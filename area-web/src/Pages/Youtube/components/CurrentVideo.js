import React from 'react';
import Paper from "@material-ui/core/Paper";
import {makeStyles} from "@material-ui/core";
import Typography from "@material-ui/core/Typography";

const useStyles = makeStyles((theme) => ({
    paper: {
        height: '500px'
    },
    info: {
        padding: theme.spacing(1)
    }
}));

function CurrentVideo(props) {
    const style = useStyles();

    if (!props.video) {
        return 'Loading....';
    }

    const videoUrl = `https://www.youtube.com/embed/${props.video.id.videoId ? props.video.id.videoId : props.video.id}`;

    return (
        <React.Fragment>
            <Paper className={style.paper} elevation={6}>
                <iframe
                    frameBorder="0"
                    height="100%"
                    width="100%"
                    title="Video Player"
                    src={videoUrl}
                />
                </Paper>
            <Paper elevation={6}>
                <Typography className={style.info} variant={'h6'}>{props.video.snippet.title}</Typography>
            </Paper>
        </React.Fragment>
    );
}

export default CurrentVideo;
