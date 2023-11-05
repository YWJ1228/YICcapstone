import { useEffect, useState } from "react";
import { API } from "../../../Config/APIConfig";
import AudioPlayer from "react-h5-audio-player";
import "react-h5-audio-player/lib/styles.css";
import { Button, ListGroup, Stack } from "react-bootstrap";

import axios from "axios";
import { List } from "@mui/material";
import classes from "./AudioBookPlayer.module.css";
export default function AudioBookPlayer(props) {
  const nowTTS = props.selectedTTS;
  const dummyCurPlay = {
    ebookName: "book1",
    ebookImage : "/Image/Novel_Image/Novel004_img.png",
    ebookAuthor: "author1",
    ebookContent: "content1",
    ttsName: "tts1",
    curTime: 0,
  };
  const dummyCurPlay2 = {
    ebookName: "book2",
    ebookImage : "/Image/Novel_Image/Novel005_img.png",
    ebookAuthor: "author2",
    ebookContent: "content2",
    ttsName: "tts2",
    curTime: 0,
  };
  const dummyCurPlay3 = {
    ebookName: "book3",
    ebookImage : "/Image/Novel_Image/Novel003_img.png",
    ebookAuthor: "author3",
    ebookContent: "content3",
    ttsName: "tts3",
    curTime: 0,
  };
  const dummyPlaylist = [
    dummyCurPlay,
    dummyCurPlay2,
    dummyCurPlay3,
    dummyCurPlay,
    dummyCurPlay2,
    dummyCurPlay3,
    dummyCurPlay,
    dummyCurPlay2,
    dummyCurPlay3,
    dummyCurPlay,
    dummyCurPlay2,
    dummyCurPlay3,
  ];
  const [curPlay, setCurPlay] = useState(dummyCurPlay);
  const [playlist, setPlaylist] = useState([]);
  //   useEffect(() => {
  //     axios.all([axios.get(API.PLAYER_LOAD_LAST_PLAYEDBOOK), axios.get(API.PLAYER_LOAD_PLAYLIST)]).then(
  //       axios.spread((res1, res2) => {
  //         setCurPlay({
  //           ebookName: res1.data.ebookName,
  //           ebookAuthor: res1.data.ebookAuthor,
  //           ebookContent: res1.data.ebookContet,
  //           ttsName: res1.data.ttsName,
  //           curTime: res1.data.curTime,
  //         });
  //         const resData2 = res2.data.content.map((audioBook)=>({
  //             audioBookId : audioBook.id,x
  //             ebookName: audioBook.ebookName,
  //             ebookAuthor: audioBook.ebookAuthor
  //             ebookContent: res1.data.ebookContet,
  //           ttsName: res1.data.ttsName,
  //           curTime: res1.data.curTime,
  //         }));
  //         setPlaylist(resData2);
  //       })
  //     );
  //   }, []);
  const playlistBlock = dummyPlaylist.map((audioBook) => {
    return (
      <ListGroup.Item className = {classes['list-item']} onClick = {()=>{setCurPlay(audioBook)}}>
          {audioBook.ebookName}
      </ListGroup.Item>
    );
  });

  return (
    <>
    <img src = {curPlay.ebookImage} className = {classes['background-image']}/>

      <Stack direction="horizontal" spacing={5}>
        <div className={classes["now-playing"]}>{curPlay.ebookName}</div>
        <ListGroup className={classes["list"]}>
          <div>적용된 TTS</div>
          {playlistBlock}
        </ListGroup>
      </Stack>
      <AudioPlayer className={classes["playing-bar"]} src="/1.wav" onPlay={(e) => console.log("onPlay")} />

    </>
  );
}
