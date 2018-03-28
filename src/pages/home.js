import React, { Component } from 'react';
import { Image, Button, View, StyleSheet } from 'react-native';
import Login from './login/login'

export default class MyHomeScreen extends Component {
    static navigationOptions = {
        tabBarLabel: 'Home11',
        // Note: By default the icon is only shown on iOS. Search the showIcon option below.
        tabBarIcon: ({ tintColor }) => (
            <Image
                source={require('../res/img/0.png')}
                style={[styles.icon, { tintColor: tintColor }]}
            />
        ),
        title: "Home"
    };

    componentWillMount() {
        this.props.navigation.navigate('Login');
    }

    render() {
        return (
            <View>
                <Image
                    source={require('../res/img/0.png')}
                    style={[styles.icon]}
                />
                <Button
                    onPress={() => this.props.navigation.navigate('Notifications')}
                    title="Go to notifications"
                />
                <Button
                    onPress={() => this.props.navigation.navigate('SecondPage')}
                    title="Go to SecondPage"
                />
            </View>
        );
    }


}

const styles = StyleSheet.create({
    icon: {
        width: 26,
        height: 26,
    },
});


