import React from 'react'
import { Menu } from 'antd';
import {  AccountBookFilled, UserOutlined } from '@ant-design/icons';
import {useHistory} from "react-router-dom"
const { SubMenu } = Menu;
const current = "app"

function Navigation() {
    const history = useHistory()
    const handleClick = (e) =>{
        history.push(e.key)
    }
    return (
        <Menu onClick={handleClick} selectedKeys={[current]} mode="horizontal">
        <Menu.Item key="/clients" icon={<UserOutlined />}>
          Clients
        </Menu.Item>
        <Menu.Item key="/comptes"  icon={<AccountBookFilled />}>
          Comptes
        </Menu.Item>
      </Menu>
    )
}

export default Navigation
