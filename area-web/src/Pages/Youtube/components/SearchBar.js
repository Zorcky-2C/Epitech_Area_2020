import React, {useState} from 'react';
import Paper from "@material-ui/core/Paper";
import TextField from "@material-ui/core/TextField";
import {makeStyles} from "@material-ui/core";

const useStyles = makeStyles((theme) => ({
    paper: {
        padding: theme.spacing(2)
    }
}));

function SearchBar(props) {

    const styles = useStyles();

    const [queryString, setQueryString] = useState('');

    const changeQueryString = (e) => {
        setQueryString(e.target.value);
    }

    const addQueryString = (e) => {
        if (e.key === 'Enter') {
            props.handleSubmit(queryString);
        }
    }

    return (
        <Paper elevation={3} className={styles.paper}>
            <TextField
                style={{margin: 8}}
                placeholder="Seacrh"
                fullWidth
                margin="normal"
                variant="outlined"
                value={queryString}
                onChange={changeQueryString}
                onKeyPress={addQueryString}
            />
        </Paper>
    );
}

export default SearchBar;