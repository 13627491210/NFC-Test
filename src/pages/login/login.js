import React, { Component } from 'react';
import { View, Button, TextInput, StyleSheet } from 'react-native';

export default class Login extends Component {

    static navigationOptions = {
        title: 'login',
        headerTitle: 'Login',
        // headerLeft:null
    };

    constructor(props) {
        super(props);

        this.state = {
            account: '',
            passWord: ''
        }

        this.loginClick = this.loginClick.bind(this)
    }

    loginClick() {
        // console.info(this)
        // console.log(this.state);
    }

    render() {
        return (
            <View>
                <TextInput value={this.state.account} onChangeText={(account) => this.setState({account})} />
                <TextInput value={this.state.passWord} onChangeText={(passWord) => this.setState({passWord})}/>
                <Button onPress={this.loginClick} title="登录" />
            </View>
        )
    }
}