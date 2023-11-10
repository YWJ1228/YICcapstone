import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

import { getCookies } from "../../Component/Cookies/LoginCookie.js";
import { API } from "../../Config/APIConfig.js";
import { DebuggingMode } from "../../Config/Config.js";

import axios from "axios";
import NavigationBar from "../../Component/NavigationBar/NavigationBar.js";

import Button from "react-bootstrap/Button";
import DetailBanner from "../../Component/ProductDetail/DetailBanner.js.js";
import DetailDescription from "../../Component/ProductDetail/DetailDescription.js";
import DetailReviews from "../../Component/ProductDetail/DetailReviews.js";
import PlayerModal from "../../Component/Modal/AudioPlayer/DemoPlayer.js";

import classes from "./VoiceDetailPage.module.css";

export default function VoiceDetailPage() {
  const { voiceID } = useParams();
  const dummyReviews = [
    {
      id: 1,
      nickname: "usermedfg1",
      uploadDate: "23.08.10",
      text: "김채원 TTS 1번 후기입니다.",
    },
    {
      id: 2,
      nickname: "usnsdfdfgme22",
      uploadDate: "23.08.12",
      text: "김채원 TTS 1번 후기입니다.김채원 TTS 1번 후기입니다.",
    },
    {
      id: 3,
      nickname: "snmasddfge1333",
      uploadDate: "23.08.13",
      text: "김채원 TTS 1번 후기입니다.김채원 TTS 1번 후기입니다.김채원 TTS 1번 후기입니다.",
    },
    {
      id: 4,
      nickname: "urnm4dfg564",
      uploadDate: "23.08.14",
      text: "김채원 TTS 1번 후기입니다.김채원 TTS 1번 후기입니다.김채원 TTS 1번 후기입니다.김채원 TTS 1번 후기입니다.",
    },
  ];
  const [voiceInfo, setVoiceInfo] = useState({
    name: "default",
    category: "default",
    jobDescription: "default",
    image: "default",
    updatedDate: "default",
    rating: 0.0,
    price: 0,
    description: "default",
    reviews: dummyReviews,
  });

  useEffect(() => {
    axios
      .get(`${API.LOAD_VOICE}/${voiceID}`)
      .then(function (response) {
        setVoiceInfo({
          id: response.data.id,
          name: response.data.celebrityName,
          category: response.data.category,
          jobDescription: response.data.comment,
          image: response.data.imageUrl,
          updatedDate: response.data.uploadedAt,
          rating: response.data.preferenceCount,
          price: response.data.price,
          description: response.data.comment,
          reviews: dummyReviews,
        });
        DebuggingMode(["TTS 정보"], [response.data]);
      })
      .catch(function (err) {
        console.log(err);
        console.log("Voice detail loading error");
      });
  }, []);
  const [show, setShow] = useState(false);
  const handlerClose = () => setShow(false);
  const handlerShow = () => setShow(true);

  return (
    <>
      <PlayerModal func = {{handlerClose, show}}/>

      <NavigationBar img_src="logo.png" />
      <div style={{ width: "100%", height: "8rem" }} />
      <div className={classes["banner-wrapper"]}>
        <DetailBanner book={voiceInfo} type="voice" demoFn={handlerShow} />
      </div>
      <div className={classes.divider}></div>
      <div className={classes["description-wrapper"]}>
        <DetailDescription title="TTS 소개" description={voiceInfo.description} />
      </div>
      <div className={classes["reviews-wrapper"]}>
        <DetailReviews title="후기" reviews={voiceInfo.reviews} />
      </div>
    </>
  );
}
