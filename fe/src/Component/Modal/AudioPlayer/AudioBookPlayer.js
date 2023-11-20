import { useEffect, useState } from "react";
import { API } from "../../../Config/APIConfig";
import AudioPlayer from "react-h5-audio-player";
import "react-h5-audio-player/lib/styles.css";
import { Button, ListGroup, Stack } from "react-bootstrap";

import axios from "axios";
import { List } from "@mui/material";
import Dropdown from "react-bootstrap/Dropdown";
import classes from "./AudioBookPlayer.module.css";
import { getCookies } from "../../Cookies/LoginCookie";
export default function AudioBookPlayer() {
  const dummyCurPlay = {
    ebookName: "책을 선택해주세요",
    imageUrl: "/Image/Novel_Image/Novel004_img.png",
    audioUrl: "/1.wav",
    author: "author1",
    content: "content1",
    curTime: 0,
  };

  const [curPlay, setCurPlay] = useState(dummyCurPlay);
  const defaultTTS = [{ name: "기본 남성 1" }, { name: "기본 여성 1" }];
  const [purchasedTTS, setPurchasedTTS] = useState([]);
  const [selectedTTS, setSelectedTTS] = useState(0);
  const [bookList, setBookList] = useState([]);

  const playlistBlock = bookList.map((audioBook, idx) => {
    return (
      <ListGroup.Item
        key={idx}
        className={classes["list-item"]}
        onClick={() => {
          setCurPlay(audioBook.ebook);
        }}
      >
        {audioBook.ebook.ebookName}
      </ListGroup.Item>
    );
  });
  const dropItem = defaultTTS.concat(purchasedTTS).map((item, idx) => {
    return (
      <Dropdown.Item eventKey={idx} className={classes["dropdown-item"]} key={idx}>
        {item.name}
      </Dropdown.Item>
    );
  });

  useEffect(() => {
    axios
      .all([
        axios.get(`${API.LOAD_PURCHASED_VOICES_SIZE}`, { headers: { Authorization: `Bearer ${getCookies("accessToken")}` } }),
        axios.get(`${API.LOAD_PURCHASED_EBOOKS_SIZE}`, { headers: { Authorization: `Bearer ${getCookies("accessToken")}` } }),
      ])
      .then(
        axios.spread((size1, size2) => {
          axios.get(`${API.LOAD_PURCHASED_VOICES}${size1.data.totalElements}`, { headers: { Authorization: `Bearer ${getCookies("accessToken")}` } }).then((res) => {
            const resData = res.data.content.map((voice) => ({
              name: voice.voiceModel.celebrityName,
            }));
            setPurchasedTTS(resData);
          });
          axios.get(`${API.LOAD_PURCHASED_EBOOKS}${size2.data.totalElements}`, { headers: { Authorization: `Bearer ${getCookies("accessToken")}` } }).then((res) => {
            setBookList(res.data.content);
          });
        })
      );
  }, [selectedTTS, curPlay]);

  return (
    <>
      <img src={curPlay.imageUrl} className={classes["background-image"]} />
      <Stack direction="horizontal" spacing={5}>
        <div className={classes["now-playing"]}>
          <Stack>
            <div className = {classes.bookName}>{curPlay.ebookName}</div>
            <div className = {classes.author}>{curPlay.author}</div>
            <div className = {classes.content}>{curPlay.content}</div>
          </Stack> 
          </div>
        <ListGroup className={classes["list"]}>
          <Stack direction="horizontal" spacing={10}>
            <div className= {classes['selected-tts']}>적용된 TTS</div>
            <Dropdown align="end" onSelect={setSelectedTTS}>
              <Button className={classes["dropdown-content"]} disabled>
                {defaultTTS.concat(purchasedTTS)[selectedTTS].name}
              </Button>
              <Dropdown.Toggle split className={classes["dropdown-btn"]}></Dropdown.Toggle>
              <Dropdown.Menu className={classes["dropdown-menu"]}>{dropItem}</Dropdown.Menu>
            </Dropdown>
          </Stack>
          {playlistBlock}
        </ListGroup>
      </Stack>
      <AudioPlayer className={classes["playing-bar"]} src={curPlay.audioUrl} onPlay={(e) => console.log("onPlay")} />
    </>
  );
}
