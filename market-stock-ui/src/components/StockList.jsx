import { React, useEffect, useState } from 'react';
import { DataGrid, getThemePaletteMode } from '@material-ui/data-grid';
import { createTheme, darken, lighten } from '@material-ui/core/styles';
import { makeStyles } from '@material-ui/styles';
import axios from 'axios';
import { OrdersList } from './OrdersList';
import { FormDialog } from './FormDialog';


export function StockList() {

const defaultTheme = createTheme();
const useStyles = makeStyles(
  (theme) => {
    const getBackgroundColor = (color) =>
      getThemePaletteMode(theme.palette) === 'dark'
        ? darken(color, 0.6)
        : lighten(color, 0.6);

    const getHoverBackgroundColor = (color) =>
      getThemePaletteMode(theme.palette) === 'dark'
        ? darken(color, 0.5)
        : lighten(color, 0.5);

    return {
      root: {
        '& .super-app-theme--Plus': {
          backgroundColor: getBackgroundColor(theme.palette.success.main),
          '&:hover': {
            backgroundColor: getHoverBackgroundColor(theme.palette.success.main),
          },
        },
        '& .super-app-theme--Loss': {
          backgroundColor: getBackgroundColor(theme.palette.error.main),
          '&:hover': {
            backgroundColor: getHoverBackgroundColor(theme.palette.error.main),
          },
        },
      },
    };
  },
  { defaultTheme },
);


  
  const columns = [
    { field: 'id', headerName: 'ID', width: 90 },
    {
      field: 'name',
      headerName: 'Name',
      width: 150,
      editable: false,
    },
    {
      field: 'lastPrice',
      headerName: 'Last Price',
      width: 150,
      editable: false,
    },
    {
      field: 'quantity',
      headerName: 'Quantity',
      type: 'number',
      width: 110,
      editable: false,
      sortable: false,
    },
    {
      field: 'ppp',
      headerName: 'Pondered Price',
      description: 'This column has a value getter and is not sortable.',
      sortable: false,
      width: 160,
    },
    {
      field: 'gain',
      headerName: 'Gain',
      description: 'This column has a value getter and is not sortable.',
      width: 160,
    },
  ];

  const [stocks, setStocks] = useState([]);
  useEffect(
      () => {
       const fetchAllStocks = async () => {
          await axios.get(process.env.REACT_APP_API_ROOT_URL + '/v1/stocks/')
          .then(res => {
            const stocks = res.data;
            console.log(stocks);
            setStocks(stocks);
          }).catch(error => {
              console.log('Error here' + error);
            });
       };
       fetchAllStocks();
      }, []
  );

  const [orders, setOrders] = useState([]);
     const fetchOrdersByStock = async(stockId) => {
          axios.get(process.env.REACT_APP_API_ROOT_URL + '/v1/stocks/' + stockId + '/orders')
          .then(res => {
            const orders = res.data;
            console.log(orders);
            setOrders(orders);
          }).catch(error => {
              console.log('Error here' + error);
            });
          };
  
  const rows = stocks.map((stock)=> {
          console.log(stock);
          return {
                  id:stock.id,
                  name:stock.name,
                  lastPrice:stock.statics.lastPrice,
                  quantity:stock.statics.quantity,
                  ppp:stock.statics.ppp,
                  gain:stock.statics.gain.toFixed(2)
                }
              });
  
  
  const classes = useStyles();


  return (
    <>
    <div className="stocks-grid">
        <div style={{ height: 400, width: '150%' }} className={classes.root}>
            <DataGrid
              rows={rows}
              columns={columns}
              pageSize={10}
              getRowClassName={(params) =>
                `super-app-theme--${params.getValue(params.id, 'gain') > 0 ? 'Plus' : 'Loss'}`
              }
              onRowClick={(params) =>
                fetchOrdersByStock(params.id)
              }
            />
          </div>
      </div>
      <div className="orders-grid">
              <OrdersList orders={orders} />
      </div>
      <FormDialog />
    </>
  );
}
