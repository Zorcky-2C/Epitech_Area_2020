import React from 'react';
import Paper from "@material-ui/core/Paper";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import {makeStyles} from "@material-ui/core";

const useStyles = makeStyles((theme) => ({
    paper: {
        display: 'flex',
        alignItems: 'center',
        flexDirection: 'column',
        margin: '20px 0',
        padding: '15px'
    },
    videoImg: {
        marginBottom: '15px',
        cursor: 'pointer',
    }
}));

function VideoList(props) {
    const videos = props.videos.map(video => <Video key={video.etag} video={video} setSelectedVideo={props.setSelectedVideo}/>);

    return (
        <React.Fragment>
            {videos}
        </React.Fragment>
    );
}

const Video = (props) => {
    const styles = useStyles();
    return (
        <Grid item xs={12}>
            <Paper className={styles.paper}>
                <img className={styles.videoImg} alt="thumbnail" src={props.video.snippet.thumbnails.medium.url} onClick={() => {props.setSelectedVideo(props.video)}}/>
                <Typography variant="subtitle1">
                    <b>{props.video.snippet.title}</b>
                </Typography>
            </Paper>
        </Grid>
    )
}

export default VideoList;