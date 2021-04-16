import React, { useState } from 'react'
import Paper from '@material-ui/core/Paper';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import { TabPanel } from '@material-ui/lab';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import Login from './Login';
import Signup from './Signup';

const SignInOutContainer = () => {
    const [value, setValue] = useState(0);
    const backgroundStyle = { backgroundColor: '#1bbd7e' };

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    const paperStyle = {
        width: "340px",
        height: "556px",
        margin: "20px auto"
    };

    function TabPanel(props) {
        const { children, value, index, ...other } = props;

        return (
            <div
                role="tabpanel"
                hidden={value !== index}
                id={`full-width-tabpanel-${index}`}
                aria-labelledby={`full-width-tab-${index}`}
                {...other}
            >
                {value === index && (
                    <Box p={3}>
                        <Typography>{children}</Typography>
                    </Box>
                )}
            </div>
        );
    }

    return (
        <Paper
            elevation={20}
            style={paperStyle}>
            <style>{'body { background: linear-gradient(90deg, rgba(168,230,19,1) 0%, rgba(0,255,205,1) 34%, rgba(30,140,94,1) 100%); }'}</style>
            <Tabs
                value={value}
                indicatorColor="primary"
                textColor="primary"
                onChange={handleChange}
                aria-label="disabled tabs example"
            >
                <Tab label="Sign In" />
                <Tab label="Sign Up" />
            </Tabs>
            <TabPanel value={value} index={0}>
                <Login handleChange={handleChange} />
            </TabPanel>
            <TabPanel value={value} index={1}>
                <Signup />
            </TabPanel>
        </Paper>
    )
}

export default SignInOutContainer;