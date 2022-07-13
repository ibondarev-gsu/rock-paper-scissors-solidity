rock-paper-scissors-solidity> web3j generate truffle --truffle-json blockchain\build\contracts\RockPaperScissors.json --outputDir backend\src\main\java -p com.rockpaperscissors.contracts
rock-paper-scissors-solidity> web3j generate truffle --truffle-json blockchain\artifacts\contracts\RockPaperScissors.sol\RockPaperScissors.json --outputDir backend\src\main\java -p com.rockpaperscissors.contracts

npx hardhat console --network localhost
const first = (await ethers.getSigners())[0];
first.sendTransaction({value: ethers.utils.parseEther('2', 'ether'), to: '0x8a9c621B0d74Feeb3A09a9a5187D4d1Ef7bbc4E4'});
