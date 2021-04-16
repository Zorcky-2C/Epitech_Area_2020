import { Avatar } from "@material-ui/core";
import ArrowForwardIcon from '@material-ui/icons/ArrowForward';
import React from 'react';
import './Card.css'
import { useHistory } from "react-router-dom";

function Card({ src, title, description, route }) {
    const history = useHistory();
    return (
        <div className='card'>
            <img src={src} alt="" />
            <div className="card__info">
                <h2>{title}</h2>
                <h4>{description}</h4>
            </div>
            <ArrowForwardIcon onClick={() => history.push(route)} variant='outlined'>
            </ArrowForwardIcon>
        </div>
    )
}

export default Card
