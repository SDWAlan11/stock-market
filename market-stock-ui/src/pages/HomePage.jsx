import { React, useEffect, useState } from 'react';
import './HomePage.scss';
import { StockList } from '../components/StockList';
import { OrdersList } from '../components/OrdersList';
import axios from 'axios';



export const HomePage = () => {

    return (
        <div className="HomePage">
            <div className="header-section">
                <h1 className="app-name">Stocks Market Panel</h1>
            </div>
            <div>
                <StockList />
            </div>
        </div>
        
    );
}