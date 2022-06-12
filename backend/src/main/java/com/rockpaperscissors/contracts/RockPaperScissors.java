package com.rockpaperscissors.contracts;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class RockPaperScissors extends Contract {
    public static final String BINARY = "0x60806040523480156200001157600080fd5b5062000032620000266200003860201b60201c565b6200004060201b60201c565b62000104565b600033905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b612a5480620001146000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c80639a42f3aa116100715780639a42f3aa146101695780639ca8249814610185578063eadddb3c146101b6578063f2f03877146101d4578063f2fde38b146101f0578063f9a5918f1461020c576100a9565b80631bae0ac8146100ae5780633495655a146100e15780634783f4a614610111578063715018a6146101415780638da5cb5b1461014b575b600080fd5b6100c860048036038101906100c39190611dbb565b610228565b6040516100d89493929190611f93565b60405180910390f35b6100fb60048036038101906100f69190611dbb565b61041b565b60405161010891906120b7565b60405180910390f35b61012b60048036038101906101269190612219565b610657565b6040516101389190612271565b60405180910390f35b610149610680565b005b610153610708565b604051610160919061229b565b60405180910390f35b610183600480360381019061017e9190612307565b610731565b005b61019f600480360381019061019a919061235a565b610e6a565b6040516101ad92919061240f565b60405180910390f35b6101be610ea4565b6040516101cb919061243f565b60405180910390f35b6101ee60048036038101906101e9919061245a565b610f2a565b005b61020a600480360381019061020591906124c6565b61159a565b005b610226600480360381019061022191906124f3565b611691565b005b6002602052806000526040600020600091509050806000015490806001016040518060a00160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016000820160149054906101000a900460ff161515151581526020016000820160159054906101000a900460ff161515151581526020016000820160169054906101000a900460ff16600381111561030257610301611e53565b5b600381111561031457610313611e53565b5b815260200160018201548152505090806003016040518060a00160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016000820160149054906101000a900460ff161515151581526020016000820160159054906101000a900460ff161515151581526020016000820160169054906101000a900460ff1660038111156103e3576103e2611e53565b5b60038111156103f5576103f4611e53565b5b8152602001600182015481525050908060050160009054906101000a900460ff16905084565b610423611ccd565b6002600083815260200190815260200160002060405180608001604052908160008201548152602001600182016040518060a00160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016000820160149054906101000a900460ff161515151581526020016000820160159054906101000a900460ff161515151581526020016000820160169054906101000a900460ff16600381111561050c5761050b611e53565b5b600381111561051e5761051d611e53565b5b81526020016001820154815250508152602001600382016040518060a00160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016000820160149054906101000a900460ff161515151581526020016000820160159054906101000a900460ff161515151581526020016000820160169054906101000a900460ff1660038111156105f1576105f0611e53565b5b600381111561060357610602611e53565b5b815260200160018201548152505081526020016005820160009054906101000a900460ff16600281111561063a57610639611e53565b5b600281111561064c5761064b611e53565b5b815250509050919050565b6000808290506000815103610672576000801b91505061067b565b60208301519150505b919050565b610688611ab4565b73ffffffffffffffffffffffffffffffffffffffff166106a6610708565b73ffffffffffffffffffffffffffffffffffffffff16146106fc576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106f390612590565b60405180910390fd5b6107066000611abc565b565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60006002600085815260200190815260200160002060405180608001604052908160008201548152602001600182016040518060a00160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016000820160149054906101000a900460ff161515151581526020016000820160159054906101000a900460ff161515151581526020016000820160169054906101000a900460ff16600381111561081c5761081b611e53565b5b600381111561082e5761082d611e53565b5b81526020016001820154815250508152602001600382016040518060a00160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016000820160149054906101000a900460ff161515151581526020016000820160159054906101000a900460ff161515151581526020016000820160169054906101000a900460ff16600381111561090157610900611e53565b5b600381111561091357610912611e53565b5b815260200160018201548152505081526020016005820160009054906101000a900460ff16600281111561094a57610949611e53565b5b600281111561095c5761095b611e53565b5b8152505090506001600281111561097657610975611e53565b5b8160600151600281111561098d5761098c611e53565b5b146109cd576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016109c4906125fc565b60405180910390fd5b3373ffffffffffffffffffffffffffffffffffffffff1681602001516000015173ffffffffffffffffffffffffffffffffffffffff161480610a4257503373ffffffffffffffffffffffffffffffffffffffff1681604001516000015173ffffffffffffffffffffffffffffffffffffffff16145b610a81576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a7890612668565b60405180910390fd5b60016003811115610a9557610a94611e53565b5b836003811115610aa857610aa7611e53565b5b1480610ad8575060026003811115610ac357610ac2611e53565b5b836003811115610ad657610ad5611e53565b5b145b80610b065750600380811115610af157610af0611e53565b5b836003811115610b0457610b03611e53565b5b145b610b45576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610b3c906126d4565b60405180910390fd5b3373ffffffffffffffffffffffffffffffffffffffff1681602001516000015173ffffffffffffffffffffffffffffffffffffffff1603610b9c57610b8f81602001518484611b80565b8160200181905250610bb4565b610bab81604001518484611b80565b81604001819052505b8060200151604001518015610bce57508060400151604001515b15610c4157600281606001906002811115610bec57610beb611e53565b5b90816002811115610c0057610bff611e53565b5b81525050837fdf37221aaffab5daec18329c74e9de71f26727289dfefd6e144987965be79ef38260600151604051610c3891906126f4565b60405180910390a25b80600260008681526020019081526020016000206000820151816000015560208201518160010160008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160000160146101000a81548160ff02191690831515021790555060408201518160000160156101000a81548160ff02191690831515021790555060608201518160000160166101000a81548160ff02191690836003811115610d1a57610d19611e53565b5b021790555060808201518160010155505060408201518160030160008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160000160146101000a81548160ff02191690831515021790555060408201518160000160156101000a81548160ff02191690831515021790555060608201518160000160166101000a81548160ff02191690836003811115610de657610de5611e53565b5b021790555060808201518160010155505060608201518160050160006101000a81548160ff02191690836002811115610e2257610e21611e53565b5b0217905550905050837fc68416cfb4fec2fce79abcfa27c33ba8c9a63168b3b70d1cd3449b4a973465a83385604051610e5c92919061271e565b60405180910390a250505050565b6060600033600184604051602001610e8493929190612747565b604051602081830303815290604052915081805190602001209050915091565b6000610eae611ab4565b73ffffffffffffffffffffffffffffffffffffffff16610ecc610708565b73ffffffffffffffffffffffffffffffffffffffff1614610f22576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610f1990612590565b60405180910390fd5b600154905090565b60006002600084815260200190815260200160002060405180608001604052908160008201548152602001600182016040518060a00160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016000820160149054906101000a900460ff161515151581526020016000820160159054906101000a900460ff161515151581526020016000820160169054906101000a900460ff16600381111561101557611014611e53565b5b600381111561102757611026611e53565b5b81526020016001820154815250508152602001600382016040518060a00160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016000820160149054906101000a900460ff161515151581526020016000820160159054906101000a900460ff161515151581526020016000820160169054906101000a900460ff1660038111156110fa576110f9611e53565b5b600381111561110c5761110b611e53565b5b815260200160018201548152505081526020016005820160009054906101000a900460ff16600281111561114357611142611e53565b5b600281111561115557611154611e53565b5b8152505090503373ffffffffffffffffffffffffffffffffffffffff1681602001516000015173ffffffffffffffffffffffffffffffffffffffff1614806111d057503373ffffffffffffffffffffffffffffffffffffffff1681604001516000015173ffffffffffffffffffffffffffffffffffffffff16145b61120f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161120690612668565b60405180910390fd5b6000600281111561122357611222611e53565b5b8160600151600281111561123a57611239611e53565b5b1461127a576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611271906125fc565b60405180910390fd5b3373ffffffffffffffffffffffffffffffffffffffff1681602001516000015173ffffffffffffffffffffffffffffffffffffffff16036112d0576112c3816020015183611c5c565b81602001819052506112e7565b6112de816040015183611c5c565b81604001819052505b806020015160200151801561130157508060400151602001515b156113745760018160600190600281111561131f5761131e611e53565b5b9081600281111561133357611332611e53565b5b81525050827fdf37221aaffab5daec18329c74e9de71f26727289dfefd6e144987965be79ef3826060015160405161136b91906126f4565b60405180910390a25b80600260008581526020019081526020016000206000820151816000015560208201518160010160008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160000160146101000a81548160ff02191690831515021790555060408201518160000160156101000a81548160ff02191690831515021790555060608201518160000160166101000a81548160ff0219169083600381111561144d5761144c611e53565b5b021790555060808201518160010155505060408201518160030160008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160000160146101000a81548160ff02191690831515021790555060408201518160000160156101000a81548160ff02191690831515021790555060608201518160000160166101000a81548160ff0219169083600381111561151957611518611e53565b5b021790555060808201518160010155505060608201518160050160006101000a81548160ff0219169083600281111561155557611554611e53565b5b0217905550905050827f775cbcccd7fe28145ecb9139488663063065c5a215ba96419500f1bb1217661e3360405161158d919061229b565b60405180910390a2505050565b6115a2611ab4565b73ffffffffffffffffffffffffffffffffffffffff166115c0610708565b73ffffffffffffffffffffffffffffffffffffffff1614611616576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161160d90612590565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603611685576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161167c906127f0565b60405180910390fd5b61168e81611abc565b50565b611699611ab4565b73ffffffffffffffffffffffffffffffffffffffff166116b7610708565b73ffffffffffffffffffffffffffffffffffffffff161461170d576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161170490612590565b60405180910390fd5b8073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff160361177b576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016117729061285c565b60405180910390fd5b60006040518060a001604052808473ffffffffffffffffffffffffffffffffffffffff168152602001600015158152602001600015158152602001600060038111156117ca576117c9611e53565b5b81526020016000801b815250905060006040518060a001604052808473ffffffffffffffffffffffffffffffffffffffff1681526020016000151581526020016000151581526020016000600381111561182757611826611e53565b5b81526020016000801b8152509050604051806080016040528060015481526020018381526020018281526020016000600281111561186857611867611e53565b5b8152506002600060015481526020019081526020016000206000820151816000015560208201518160010160008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160000160146101000a81548160ff02191690831515021790555060408201518160000160156101000a81548160ff02191690831515021790555060608201518160000160166101000a81548160ff0219169083600381111561194557611944611e53565b5b021790555060808201518160010155505060408201518160030160008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160000160146101000a81548160ff02191690831515021790555060408201518160000160156101000a81548160ff02191690831515021790555060608201518160000160166101000a81548160ff02191690836003811115611a1157611a10611e53565b5b021790555060808201518160010155505060608201518160050160006101000a81548160ff02191690836002811115611a4d57611a4c611e53565b5b02179055509050507fcd359a5a81a67a8cba1fa58a9d77fcf61bd58c6ebc6703100aa52cceefffa08d60015485854342604051611a8e95949392919061287c565b60405180910390a160016000815480929190611aa9906128fe565b919050555050505050565b600033905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b611b88611d13565b836040015115611bcd576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611bc490612992565b60405180910390fd5b836080015184600001518484604051602001611beb93929190612747565b6040516020818303038152906040528051906020012014611c41576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611c38906129fe565b60405180910390fd5b60018460400190151590811515815250508390509392505050565b611c64611d13565b826020015115611ca9576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611ca090612992565b60405180910390fd5b81836080018181525050600183602001901515908115158152505082905092915050565b604051806080016040528060008152602001611ce7611d13565b8152602001611cf4611d13565b815260200160006002811115611d0d57611d0c611e53565b5b81525090565b6040518060a00160405280600073ffffffffffffffffffffffffffffffffffffffff16815260200160001515815260200160001515815260200160006003811115611d6157611d60611e53565b5b8152602001600080191681525090565b6000604051905090565b600080fd5b600080fd5b6000819050919050565b611d9881611d85565b8114611da357600080fd5b50565b600081359050611db581611d8f565b92915050565b600060208284031215611dd157611dd0611d7b565b5b6000611ddf84828501611da6565b91505092915050565b611df181611d85565b82525050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000611e2282611df7565b9050919050565b611e3281611e17565b82525050565b60008115159050919050565b611e4d81611e38565b82525050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b60048110611e9357611e92611e53565b5b50565b6000819050611ea482611e82565b919050565b6000611eb482611e96565b9050919050565b611ec481611ea9565b82525050565b6000819050919050565b611edd81611eca565b82525050565b60a082016000820151611ef96000850182611e29565b506020820151611f0c6020850182611e44565b506040820151611f1f6040850182611e44565b506060820151611f326060850182611ebb565b506080820151611f456080850182611ed4565b50505050565b60038110611f5c57611f5b611e53565b5b50565b6000819050611f6d82611f4b565b919050565b6000611f7d82611f5f565b9050919050565b611f8d81611f72565b82525050565b600061018082019050611fa96000830187611de8565b611fb66020830186611ee3565b611fc360c0830185611ee3565b611fd1610160830184611f84565b95945050505050565b611fe381611d85565b82525050565b60a082016000820151611fff6000850182611e29565b5060208201516120126020850182611e44565b5060408201516120256040850182611e44565b5060608201516120386060850182611ebb565b50608082015161204b6080850182611ed4565b50505050565b61205a81611f72565b82525050565b610180820160008201516120776000850182611fda565b50602082015161208a6020850182611fe9565b50604082015161209d60c0850182611fe9565b5060608201516120b1610160850182612051565b50505050565b6000610180820190506120cd6000830184612060565b92915050565b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b612126826120dd565b810181811067ffffffffffffffff82111715612145576121446120ee565b5b80604052505050565b6000612158611d71565b9050612164828261211d565b919050565b600067ffffffffffffffff821115612184576121836120ee565b5b61218d826120dd565b9050602081019050919050565b82818337600083830152505050565b60006121bc6121b784612169565b61214e565b9050828152602081018484840111156121d8576121d76120d8565b5b6121e384828561219a565b509392505050565b600082601f830112612200576121ff6120d3565b5b81356122108482602086016121a9565b91505092915050565b60006020828403121561222f5761222e611d7b565b5b600082013567ffffffffffffffff81111561224d5761224c611d80565b5b612259848285016121eb565b91505092915050565b61226b81611eca565b82525050565b60006020820190506122866000830184612262565b92915050565b61229581611e17565b82525050565b60006020820190506122b0600083018461228c565b92915050565b600481106122c357600080fd5b50565b6000813590506122d5816122b6565b92915050565b6122e481611eca565b81146122ef57600080fd5b50565b600081359050612301816122db565b92915050565b6000806000606084860312156123205761231f611d7b565b5b600061232e86828701611da6565b935050602061233f868287016122c6565b9250506040612350868287016122f2565b9150509250925092565b6000602082840312156123705761236f611d7b565b5b600061237e848285016122f2565b91505092915050565b600081519050919050565b600082825260208201905092915050565b60005b838110156123c15780820151818401526020810190506123a6565b838111156123d0576000848401525b50505050565b60006123e182612387565b6123eb8185612392565b93506123fb8185602086016123a3565b612404816120dd565b840191505092915050565b6000604082019050818103600083015261242981856123d6565b90506124386020830184612262565b9392505050565b60006020820190506124546000830184611de8565b92915050565b6000806040838503121561247157612470611d7b565b5b600061247f85828601611da6565b9250506020612490858286016122f2565b9150509250929050565b6124a381611e17565b81146124ae57600080fd5b50565b6000813590506124c08161249a565b92915050565b6000602082840312156124dc576124db611d7b565b5b60006124ea848285016124b1565b91505092915050565b6000806040838503121561250a57612509611d7b565b5b6000612518858286016124b1565b9250506020612529858286016124b1565b9150509250929050565b600082825260208201905092915050565b7f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572600082015250565b600061257a602083612533565b915061258582612544565b602082019050919050565b600060208201905081810360008301526125a98161256d565b9050919050565b7f53746167653a2077726f6e672073746174757321000000000000000000000000600082015250565b60006125e6601483612533565b91506125f1826125b0565b602082019050919050565b60006020820190508181036000830152612615816125d9565b9050919050565b7f506c617965723a206973206e6f7420696e20726f6f6d21000000000000000000600082015250565b6000612652601783612533565b915061265d8261261c565b602082019050919050565b6000602082019050818103600083015261268181612645565b9050919050565b7f43686f6963653a20696e76616c69642063686f69636521000000000000000000600082015250565b60006126be601783612533565b91506126c982612688565b602082019050919050565b600060208201905081810360008301526126ed816126b1565b9050919050565b60006020820190506127096000830184611f84565b92915050565b61271881611ea9565b82525050565b6000604082019050612733600083018561228c565b612740602083018461270f565b9392505050565b600060608201905061275c600083018661228c565b612769602083018561270f565b6127766040830184612262565b949350505050565b7f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160008201527f6464726573730000000000000000000000000000000000000000000000000000602082015250565b60006127da602683612533565b91506127e58261277e565b604082019050919050565b60006020820190508181036000830152612809816127cd565b9050919050565b7f416464726573733a2073616d652076616c756521000000000000000000000000600082015250565b6000612846601483612533565b915061285182612810565b602082019050919050565b6000602082019050818103600083015261287581612839565b9050919050565b600060a0820190506128916000830188611de8565b61289e602083018761228c565b6128ab604083018661228c565b6128b86060830185611de8565b6128c56080830184611de8565b9695505050505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b600061290982611d85565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff820361293b5761293a6128cf565b5b600182019050919050565b7f506c617965723a20616c726561647920636f6d6d697465642100000000000000600082015250565b600061297c601983612533565b915061298782612946565b602082019050919050565b600060208201905081810360008301526129ab8161296f565b9050919050565b7f436f6d6d69746d656e743a20696e76616c696420686173682100000000000000600082015250565b60006129e8601983612533565b91506129f3826129b2565b602082019050919050565b60006020820190508181036000830152612a17816129db565b905091905056fea2646970667358221220ac901f7f280f972b429ceb2243c5e13a30de34b3528d75b0b09f68d07a6841d964736f6c634300080e0033";

    public static final String FUNC_CREATEROOM = "createRoom";

    public static final String FUNC_GETROOMBYID = "getRoomById";

    public static final String FUNC_GETROOMCOUNT = "getRoomCount";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_ROOMS = "rooms";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_COMMIT = "commit";

    public static final String FUNC_REVEAL = "reveal";

    public static final String FUNC_CONVERTSTRINGTOBYTES32 = "convertStringToBytes32";

    public static final String FUNC_TESTHASH = "testHash";

    public static final Event COMMIT_EVENT = new Event("Commit", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event REVEAL_EVENT = new Event("Reveal", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>() {}, new TypeReference<Uint8>() {}));
    ;

    public static final Event ROOMCREATED_EVENT = new Event("RoomCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event STAGECHANGED_EVENT = new Event("StageChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint8>() {}));
    ;

    public static final Event TEST_EVENT = new Event("Test", 
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}, new TypeReference<Bytes32>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected RockPaperScissors(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected RockPaperScissors(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected RockPaperScissors(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected RockPaperScissors(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<CommitEventResponse> getCommitEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(COMMIT_EVENT, transactionReceipt);
        ArrayList<CommitEventResponse> responses = new ArrayList<CommitEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CommitEventResponse typedResponse = new CommitEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._player = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CommitEventResponse> commitEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CommitEventResponse>() {
            @Override
            public CommitEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(COMMIT_EVENT, log);
                CommitEventResponse typedResponse = new CommitEventResponse();
                typedResponse.log = log;
                typedResponse._id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._player = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CommitEventResponse> commitEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(COMMIT_EVENT));
        return commitEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<RevealEventResponse> getRevealEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REVEAL_EVENT, transactionReceipt);
        ArrayList<RevealEventResponse> responses = new ArrayList<RevealEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RevealEventResponse typedResponse = new RevealEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._player = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._choice = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RevealEventResponse> revealEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RevealEventResponse>() {
            @Override
            public RevealEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REVEAL_EVENT, log);
                RevealEventResponse typedResponse = new RevealEventResponse();
                typedResponse.log = log;
                typedResponse._id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._player = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._choice = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RevealEventResponse> revealEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REVEAL_EVENT));
        return revealEventFlowable(filter);
    }

    public List<RoomCreatedEventResponse> getRoomCreatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ROOMCREATED_EVENT, transactionReceipt);
        ArrayList<RoomCreatedEventResponse> responses = new ArrayList<RoomCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RoomCreatedEventResponse typedResponse = new RoomCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._firstPlayer = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._secondPlayer = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._blockNumber = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse._timestamp = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RoomCreatedEventResponse> roomCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RoomCreatedEventResponse>() {
            @Override
            public RoomCreatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ROOMCREATED_EVENT, log);
                RoomCreatedEventResponse typedResponse = new RoomCreatedEventResponse();
                typedResponse.log = log;
                typedResponse._id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._firstPlayer = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._secondPlayer = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._blockNumber = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse._timestamp = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RoomCreatedEventResponse> roomCreatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROOMCREATED_EVENT));
        return roomCreatedEventFlowable(filter);
    }

    public List<StageChangedEventResponse> getStageChangedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(STAGECHANGED_EVENT, transactionReceipt);
        ArrayList<StageChangedEventResponse> responses = new ArrayList<StageChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            StageChangedEventResponse typedResponse = new StageChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._stage = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<StageChangedEventResponse> stageChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, StageChangedEventResponse>() {
            @Override
            public StageChangedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(STAGECHANGED_EVENT, log);
                StageChangedEventResponse typedResponse = new StageChangedEventResponse();
                typedResponse.log = log;
                typedResponse._id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._stage = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<StageChangedEventResponse> stageChangedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(STAGECHANGED_EVENT));
        return stageChangedEventFlowable(filter);
    }

    public List<TestEventResponse> getTestEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TEST_EVENT, transactionReceipt);
        ArrayList<TestEventResponse> responses = new ArrayList<TestEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TestEventResponse typedResponse = new TestEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._encode = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._keccak = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TestEventResponse> testEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TestEventResponse>() {
            @Override
            public TestEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TEST_EVENT, log);
                TestEventResponse typedResponse = new TestEventResponse();
                typedResponse.log = log;
                typedResponse._encode = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._keccak = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TestEventResponse> testEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TEST_EVENT));
        return testEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> createRoom(String _firstPlayerAddress, String _secondPlayerAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATEROOM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_firstPlayerAddress), 
                new org.web3j.abi.datatypes.Address(_secondPlayerAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Room> getRoomById(BigInteger _id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETROOMBYID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Room>() {}));
        return executeRemoteCallSingleValueReturn(function, Room.class);
    }

    public RemoteFunctionCall<BigInteger> getRoomCount() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETROOMCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, Player, Player, BigInteger>> rooms(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ROOMS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Player>() {}, new TypeReference<Player>() {}, new TypeReference<Uint8>() {}));
        return new RemoteFunctionCall<Tuple4<BigInteger, Player, Player, BigInteger>>(function,
                new Callable<Tuple4<BigInteger, Player, Player, BigInteger>>() {
                    @Override
                    public Tuple4<BigInteger, Player, Player, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, Player, Player, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (Player) results.get(1), 
                                (Player) results.get(2), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> commit(BigInteger _roomId, byte[] _commitment) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_COMMIT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_roomId), 
                new org.web3j.abi.datatypes.generated.Bytes32(_commitment)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> reveal(BigInteger _roomId, BigInteger _choice, byte[] _key) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REVEAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_roomId), 
                new org.web3j.abi.datatypes.generated.Uint8(_choice), 
                new org.web3j.abi.datatypes.generated.Bytes32(_key)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<byte[]> convertStringToBytes32(String source) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CONVERTSTRINGTOBYTES32, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(source)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<Tuple2<byte[], byte[]>> testHash(byte[] _blindingFactor) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TESTHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_blindingFactor)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteFunctionCall<Tuple2<byte[], byte[]>>(function,
                new Callable<Tuple2<byte[], byte[]>>() {
                    @Override
                    public Tuple2<byte[], byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<byte[], byte[]>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue());
                    }
                });
    }

    @Deprecated
    public static RockPaperScissors load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new RockPaperScissors(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static RockPaperScissors load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RockPaperScissors(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RockPaperScissors load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new RockPaperScissors(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static RockPaperScissors load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new RockPaperScissors(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<RockPaperScissors> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RockPaperScissors.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<RockPaperScissors> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RockPaperScissors.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<RockPaperScissors> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RockPaperScissors.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<RockPaperScissors> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RockPaperScissors.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class Player extends StaticStruct {
        public String playerAddress;

        public Boolean commited;

        public Boolean revealed;

        public BigInteger choice;

        public byte[] commitment;

        public Player(String playerAddress, Boolean commited, Boolean revealed, BigInteger choice, byte[] commitment) {
            super(new org.web3j.abi.datatypes.Address(playerAddress),new org.web3j.abi.datatypes.Bool(commited),new org.web3j.abi.datatypes.Bool(revealed),new org.web3j.abi.datatypes.generated.Uint8(choice),new org.web3j.abi.datatypes.generated.Bytes32(commitment));
            this.playerAddress = playerAddress;
            this.commited = commited;
            this.revealed = revealed;
            this.choice = choice;
            this.commitment = commitment;
        }

        public Player(Address playerAddress, Bool commited, Bool revealed, Uint8 choice, Bytes32 commitment) {
            super(playerAddress,commited,revealed,choice,commitment);
            this.playerAddress = playerAddress.getValue();
            this.commited = commited.getValue();
            this.revealed = revealed.getValue();
            this.choice = choice.getValue();
            this.commitment = commitment.getValue();
        }
    }

    public static class Room extends StaticStruct {
        public BigInteger id;

        public Player firstPlayer;

        public Player secondPlayer;

        public BigInteger stage;

        public Room(BigInteger id, Player firstPlayer, Player secondPlayer, BigInteger stage) {
            super(new org.web3j.abi.datatypes.generated.Uint256(id),firstPlayer,secondPlayer,new org.web3j.abi.datatypes.generated.Uint8(stage));
            this.id = id;
            this.firstPlayer = firstPlayer;
            this.secondPlayer = secondPlayer;
            this.stage = stage;
        }

        public Room(Uint256 id, Player firstPlayer, Player secondPlayer, Uint8 stage) {
            super(id,firstPlayer,secondPlayer,stage);
            this.id = id.getValue();
            this.firstPlayer = firstPlayer;
            this.secondPlayer = secondPlayer;
            this.stage = stage.getValue();
        }
    }

    public static class CommitEventResponse extends BaseEventResponse {
        public BigInteger _id;

        public String _player;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class RevealEventResponse extends BaseEventResponse {
        public BigInteger _id;

        public String _player;

        public BigInteger _choice;
    }

    public static class RoomCreatedEventResponse extends BaseEventResponse {
        public BigInteger _id;

        public String _firstPlayer;

        public String _secondPlayer;

        public BigInteger _blockNumber;

        public BigInteger _timestamp;
    }

    public static class StageChangedEventResponse extends BaseEventResponse {
        public BigInteger _id;

        public BigInteger _stage;
    }

    public static class TestEventResponse extends BaseEventResponse {
        public byte[] _encode;

        public byte[] _keccak;
    }
}
