import { React, useEffect, useState } from 'react';
import axios from 'axios';
import { DataGrid, getThemePaletteMode } from '@material-ui/data-grid';
import { createTheme, darken, lighten } from '@material-ui/core/styles';
import { makeStyles } from '@material-ui/styles';

export function OrdersList({ orders }) {

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
        '& .super-app-theme--SELL': {
          backgroundColor: getBackgroundColor(theme.palette.info.main),
          '&:hover': {
            backgroundColor: getHoverBackgroundColor(theme.palette.info.main),
          },
        },
        '& .super-app-theme--BUY': {
          backgroundColor: getBackgroundColor(theme.palette.success.main),
          '&:hover': {
            backgroundColor: getHoverBackgroundColor(theme.palette.success.main),
          },
        },
      },
    };
  },
  { defaultTheme },
);


  
  const columns = [
    
    {
      field: 'sense',
      headerName: 'Sense',
      width: 150,
      editable: false,
    },
    {
      field: 'quantity',
      headerName: 'Quantity',
      width: 150,
      editable: false,
    },
    {
      field: 'price',
      headerName: 'Price',
      width: 150,
      editable: false,
    },
  ];


  
  const classes = useStyles();


  return (
    <div style={{ height: 400, width: '170%' }} className={classes.root}>
        <DataGrid
          rows={orders}
          columns={columns}
          pageSize={10}
          getRowClassName={(params) =>
            `super-app-theme--${params.getValue(params.id, 'sense')}`
          }
        />
        <input class="btn btn-primary" type="button" value="Buy" onClick={() => console.log("BUY")}/>
        <input class="btn btn-primary" type="button" value="Sell" onClick={() => console.log("SELL")}/>
    </div>
  );
}
