import React,{useEffect, useState} from 'react'
import {Table } from "antd"
import api, { COMPTEOPS_SERVICE} from "../api"
const columns = [
    {
      title: 'solde',
      dataIndex: 'solde',
      key: 'solde',
     
    },
    {
      title: 'Type',
      dataIndex: 'type',
      key: 'type',
    },
    {
      title: 'Etat',
      dataIndex: 'etat',
      key: 'etat',
    },  {
        title: 'Date Creation',
        dataIndex: 'created_at',
        key: 'created_at',
      }
 ]
function Comptes() {
    const [comptesData, setcomptesData] = useState([])
    useEffect(() => {
        api.get(`${COMPTEOPS_SERVICE}/comptes`)
        .then(resp =>{
            setcomptesData(resp.data._embedded.comptes)
        })
      
    }, [])
    return (
        <Table columns={columns} dataSource={comptesData} />
    )
}

export default Comptes
