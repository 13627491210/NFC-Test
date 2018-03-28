import React,{ Component } from 'react';
import { Button } from 'react-native';
import { StackNavigator } from 'react-navigation';

export default class SecondPage extends Component {
    render(){
        return (<Button
            onPress={() => this.props.navigation.navigate('Notifications')}
            title="SecondPage"
        />)
    }
}

// const SecondPageNav = StackNavigator({
//     SecondPage:{
//         screen:SecondPage
//     }
// })

// export default SecondPageNav;

