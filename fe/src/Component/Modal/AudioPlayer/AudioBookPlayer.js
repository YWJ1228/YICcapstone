import { useEffect, useState } from "react";
import { API } from "../../../Config/APIConfig";
import AudioPlayer from "react-h5-audio-player";
import "react-h5-audio-player/lib/styles.css";
import { Button, ListGroup, Stack, Row, Col, Container } from "react-bootstrap";

import axios from "axios";
import { List } from "@mui/material";
import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import Dropdown from "react-bootstrap/Dropdown";
import classes from "./AudioBookPlayer.module.css";
import { getCookies } from "../../Cookies/LoginCookie";
export default function AudioBookPlayer() {
  const [curPlay, setCurPlay] = useState("");
  const [purchasedTTS, setPurchasedTTS] = useState([{ id: -1, name: "해당없음" }]);
  const [selectedTTS, setSelectedTTS] = useState(0);
  const [bookList, setBookList] = useState([{ ebook: { name: "해당없음" } }]);
  const [selectedBook, setSelectedBook] = useState(0);



  const playlistBlock = bookList.map((audioBook, idx) => {
    return (
      <ListGroup.Item
        key={idx}
        className={classes["list-item"]}
        onClick={() => {
          setSelectedBook(idx);
        }}
      >
        <div>{audioBook.ebook.ebookName}</div>
      </ListGroup.Item>
    );
  });
  const dropItem = purchasedTTS.map((item, idx) => {
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
              id: voice.voiceModel.id,
              name: voice.voiceModel.celebrityName,
            }));
            setPurchasedTTS(resData);
          });
          axios.get(`${API.LOAD_PURCHASED_EBOOKS}${size2.data.totalElements}`, { headers: { Authorization: `Bearer ${getCookies("accessToken")}` } }).then((res) => {
            setBookList(res.data.content);
          });
        })
      )
      .catch((err) => {
        // console.log(err);
      });
  }, [selectedTTS, selectedBook]);
  axios
    .get(
      `${API.LOAD_AUDIOBOOK_PATH}/${bookList[selectedBook].ebook.id}/${purchasedTTS[selectedTTS].id}`,
      { headers: { Authorization: `Bearer ${getCookies("accessToken")}` } },
      {
        params: {
          ebookId: bookList[selectedBook].ebook.id,
          voiceId: purchasedTTS[selectedTTS].id,
        },
      }
    )
    .then((res) => {
      setCurPlay(res.data.audioBookLink);

    })
    .catch((err) => {
      // console.log(err);
    });
  return (
    <>
      <img src={bookList[selectedBook].ebook.imageUrl} className={classes["background-image"]} />
      <Stack direction="horizontal" gap={5}>
        <div className={classes["now-playing"]}>
          <Stack>
            <div className={classes.bookName}>{bookList[selectedBook].ebook.ebookName}</div>
            <div className={classes.author}>{bookList[selectedBook].ebook.author}</div>
            <div className={classes.content}>{bookList[selectedBook].ebook.content}</div>
          </Stack>
        </div>
        <div className={classes.playlist}>
          <Container>
            <Row>
              <Col className={classes["selected-tts"]} xs={2} md={2}>
                적용된 TTS
              </Col>
              <Col>
                <Row>
                  <Dropdown align="end" onSelect={setSelectedTTS}>
                    <Button className={classes["dropdown-content"]} disabled>
                      {purchasedTTS[selectedTTS].name}
                    </Button>
                    <Dropdown.Toggle split className={classes["dropdown-btn"]}></Dropdown.Toggle>
                    <Dropdown.Menu className={classes["dropdown-menu"]}>{dropItem}</Dropdown.Menu>
                  </Dropdown>
                </Row>
                <Row>
                  <ListGroup className={classes["list"]}>{playlistBlock}</ListGroup>
                </Row>
              </Col>
              <Col xs={1} md={1}>
                <a href="/">
                  <HomeOutlinedIcon className={classes["home-btn"]} />
                </a>
              </Col>
            </Row>
          </Container>
        </div>
      </Stack>
      <AudioPlayer
        autoPlayAfterSrcChange = {false}
        className={classes["playing-bar"]}
        src={curPlay}
        onPlay={(e) => {
          // console.log(e);
        }}
      />
    </>
  );
}
