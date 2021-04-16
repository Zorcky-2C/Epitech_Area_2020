import React, { useState } from 'react'
import './Banner.css'
import { Button } from "@material-ui/core";
import { useHistory } from "react-router-dom";

function Banner() {
    const history = useHistory();
    const [showSearch, setShowSearch] = useState(false);

    return (
        <div className='banner'>
            <div className='banner__info'>
                <h1>Welcome to the AREA project</h1>
                <h5>
                    Best dashboard.
                </h5>
            </div>
        </div>
    )
}

export default Banner
