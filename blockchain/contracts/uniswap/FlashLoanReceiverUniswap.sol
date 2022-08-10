//SPDX-License-Identifier: Unlicense
pragma solidity 0.8.10;

import "@aave/core-v3/contracts/flashloan/base/FlashLoanReceiverBase.sol";
import "@openzeppelin/contracts/token/ERC20/IERC20.sol";

contract FlashLoanReceiverUniswap is FlashLoanReceiverBase {
    event Log(string message, uint256 val);
    event Log(string message, address val);
    event Log(string message, bytes val);

    constructor(IPoolAddressesProvider provider)
        public
        FlashLoanReceiverBase(provider)
    {}

    function flashloan(IERC20 asset, uint256 amount) external {
        uint256 balance = asset.balanceOf(address(this));
        require(balance >= amount);

        address receiverAddress = address(this);

        address[] memory assets = new address[](1);
        assets[0] = address(asset);

        uint256[] memory amounts = new uint256[](1);
        amounts[0] = amount;

        uint256[] memory interestRateModes = new uint256[](1);
        interestRateModes[0] = 0;

        address onBehalfOf = address(this);

        bytes memory params = "";

        uint16 referralCode = 0;

        POOL.flashLoan(
            receiverAddress,
            assets,
            amounts,
            interestRateModes,
            onBehalfOf,
            params,
            referralCode
        );
    }

    function executeOperation(
        address[] calldata assets,
        uint256[] calldata amounts,
        uint256[] calldata premiums,
        address initiator,
        bytes calldata params
    ) external override returns (bool) {
        for (uint256 i = 0; i < assets.length; i++) {
            emit Log("borrowed", amounts[i]);
            emit Log("fee", premiums[i]);
            emit Log("initiator", initiator);
            emit Log("params", params);

            //do some job (arbitration)

            //repayment
            uint256 amountOwing = amounts[i] + premiums[i];
            IERC20(assets[i]).approve(address(POOL), amountOwing);
        }

        return true;
    }
}
