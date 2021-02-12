import React,{useEffect, useState} from 'react'
import {Table } from "antd"
import api, { CLIENT_SERVICE} from "../api"
const columns = [
    {
      title: 'Name',
      dataIndex: 'name',
      key: 'name',
     
    },
    {
      title: 'Email',
      dataIndex: 'email',
      key: 'email',
      render: text => <a>{text}</a>,
    }
 ]
function Clients() {
    const [clientsData, setClientData] = useState([])
    useEffect(() => {
        api.get(`${CLIENT_SERVICE}/clients`)
        .then(resp =>{
            setClientData(resp.data._embedded.clients)
        })
      
    }, [])
    return (
        <Table columns={columns} dataSource={clientsData} />
    )
}

export default Clients
