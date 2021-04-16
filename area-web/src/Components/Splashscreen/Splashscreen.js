import React from 'react'
import PacmanLoader from "react-spinners/PacmanLoader";

function Splashscreen() {
    return (
        <div>
	          <PacmanLoader
            size='60'
            color='#6b5ce7'
            css={{position: 'absolute',
            left: -30,
            right: 0,
            top: 860,
            bottom: 15,
            alignItems: 'center',
            justifyContent: 'center',
            alignSelf:'center'}}/>
        </div>
    )
}

export default Splashscreen
