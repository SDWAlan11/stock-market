import { React, useEffect, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import NumberFormat from 'react-number-format';
import TreeItem from '@material-ui/lab/TreeItem';
import { OrderItem } from './OrderItem'
import axios from 'axios';
import { DataGrid } from '@material-ui/data-grid';


export function StockItem({ stock }) {
  const { id, name, orders } = stock;

  const [statics, setStatics] = useState([]);
  useEffect(
      () => {
       const fetchStockStatics = async () => {
          await axios.get(process.env.REACT_APP_API_ROOT_URL + '/v1/stocks/' + stock.id + '/statics/')
          .then(res => {
            const statics = res.data;
            console.log(statics);
            setStatics(statics);
          }).catch(error => {
              console.log('Error here' + error);
            });
       };
       fetchStockStatics();
      }, []
  );
  
  const label = <span>{name} {id} {statics.lastPrice} {statics.lastPrice} {statics.quantity} {statics.ppp} {statics.gain.toFixed(2)} </span>;

  return (
    <TreeItem nodeId={id} label={label}>
       {orders.map((order) => (
          <OrderItem key={order.id} order={order} />
        ))}
    </TreeItem>
  );
}
