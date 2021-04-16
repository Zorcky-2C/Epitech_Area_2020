import React from 'react'
import './Header.css'
import SearchIcon from "@material-ui/icons/Search";
import LanguageIcon from "@material-ui/icons/Language";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import { Avatar, Button } from "@material-ui/core";
import { Link } from "react-router-dom";
import { useHistory } from "react-router-dom";

function Header() {
    const history = useHistory();
    return (
        <div className='header'>
            <Link to='/'>
                <img
                    className="header__icon"
                    src="https://www.area-tech.fr/wp-content/uploads/2020/03/area-logo-bleu-1500px.png"
                    alt=""
                />
            </Link>
            <div className='header__right'>
                <Avatar onClick={() => history.push('/login')} variant='outlined'/>
                <Button />
            </div>
        </div>
    )
}

export default Header
