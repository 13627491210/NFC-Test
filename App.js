/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View,
  Button,
  ScrollView
} from 'react-native';

import SecondPage from './src/pages/secondPage/secondPage';
import MyHomeScreen from './src/pages/home';
import MyNotificationsScreen from './src/pages/notification';
import Login from './src/pages/login/login';

import { StackNavigator, TabNavigator } from 'react-navigation';
import ToastExample from './src/ToastModule/ToastExample';


const MainScreenNavigator = TabNavigator({
  Homee: {
    screen: MyHomeScreen
  },
  Notifications: {
    screen: MyNotificationsScreen
  }
}, {
    tabBarPosition: 'bottom',
    animationEnabled: true,
    tabBarOptions: {
      activeTintColor: '#e91e63',
    },
    
  });

const SimpleApp = StackNavigator({
  Home: {
    screen: MainScreenNavigator
  },
  SecondPage: {
    screen: SecondPage
  },
  Login:{
    screen:Login
  }
},{
  mode:'modal',
  // headerMode :'none'
})

export default SimpleApp;