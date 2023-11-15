import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

import { API } from "../../Config/APIConfig.js";
import { DebuggingMode } from "../../Config/Config.js";

import axios from "axios";
import NavigationBar from "../../Component/NavigationBar/NavigationBar.js";

import DetailBanner from "../../Component/ProductDetail/DetailBanner.js.js";
import DetailDescription from "../../Component/ProductDetail/DetailDescription.js";
import DetailReviews from "../../Component/ProductDetail/DetailReviews.js";
import PlayerModal from "../../Component/Modal/AudioPlayer/DemoPlayer.js";

import classes from "./VoiceDetailPage.module.css";

export default function VoiceDetailPage() {
  const { voiceID } = useParams();
  const [reviews, setReviews] = useState([]);
  const [curReviewPage, setCurReviewPage] = useState(0);
  const [numTotalReviewPage, setNumTotalReviewPage] = useState(1);
  const [voiceInfo, setVoiceInfo] = useState({
    name: "default",
    category: "default",
    jobDescription: "default",
    image: "default",
    updatedDate: "default",
    rating: 0.0,
    price: 0,
    description: "default",
  });

  useEffect(() => {
    axios
      .all([axios.get(`${API.LOAD_VOICE}/${voiceID}`), axios.get(`${API.LOAD_REVIEW_VOICES}${voiceID}&page=${curReviewPage}`)])
      .then(
        axios.spread((res1, res2) => {
          setReviews(res2.data.content);
          setNumTotalReviewPage(res2.data.totalPages);
          setVoiceInfo({
            id: res1.data.id,
            name: res1.data.celebrityName,
            category: res1.data.category,
            jobDescription: res1.data.comment,
            image: res1.data.imageUrl,
            updatedDate: res1.data.uploadedAt,
            rating: res1.data.preferenceCount,
            price: res1.data.price,
            description: res1.data.comment,
          });
          DebuggingMode(["TTS 정보"], [res1.data]);
        })
      )
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
      <PlayerModal func={{ handlerClose, show }} />

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
        <DetailReviews title="후기" reviews={reviews} curReviewPage = {curReviewPage} totalPages={numTotalReviewPage} setCurPage={setCurReviewPage}/>
      </div>
    </>
  );
}
