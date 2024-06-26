import { useState, useEffect } from "react";
import { API } from "../../Config/APIConfig";
import { DebuggingMode, PageConfig } from "../../Config/Config";

import axios from "axios";
import NavigationBar from "../../Component/NavigationBar/NavigationBar";
import Carousel from "react-bootstrap/Carousel";
import BannerCard from "../../Component/Card/BannerCard";

import VoicePreview from "../../Component/Preview/VoicePreview";
import VoicePreviewAll from "../../Component/Preview/VoicePreviewAll";
import classes from "./VoiceShopPage.module.css";

export default function () {
  const [bestSellerVoice, setBestSellerVoice] = useState([
    PageConfig.VOICE_PAGE_DEFAULT_STATE,
  ]);
  const [updatedVoice, setUpdatedVoice] = useState([
    PageConfig.VOICE_PAGE_DEFAULT_STATE,
  ]);
  const [bannerVoice, setBannerVoice] = useState([
    PageConfig.VOICE_PAGE_DEFAULT_STATE,
  ]);
  const stateArr = [bestSellerVoice, updatedVoice];

  useEffect(() => {
    axios
      .all([
        axios.get(`${API.LOAD_POPULAR_VOICES}`),
        axios.get(`${API.LOAD_RECENT_VOICES}`),
        axios.get(`${API.LOAD_BANNER_VOICES}`),
      ])
      .then(
        axios.spread((res1, res2, res3) => {
          const resData = res1.data.content.map((voice) => ({
            id: voice.id,
            image: voice.imageUrl,
            name: voice.celebrityName,
            category: voice.category,
            price: voice.price,
          }));
          setBestSellerVoice(resData);
          const resData2 = res2.data.content.map((voice) => ({
            id: voice.id,
            image: voice.imageUrl,
            name: voice.celebrityName,
            category: voice.category,
            price: voice.price,
          }));
          setUpdatedVoice(resData2);
          const resData3 = res3.data.content.map((voice) => ({
            id: voice.id,
            image: voice.imageUrl,
            name: voice.celebrityName,
            category: voice.category,
            description: voice.comment,
            price: voice.price,
          }));
          setBannerVoice(resData3);
          DebuggingMode(
            ["이달의 TTS", "업데이트 된 TTS", "배너"],
            [resData, resData2, resData3]
          );
        })
      )
      .catch((err) => {
        // console.log(err)
      });
  }, []);
  const BannerList = bannerVoice.map((voice, idx) => {
    // 배너 리스트 디자인
    return (
      <Carousel.Item key={idx}>
        <BannerCard
          imagePath={voice.image}
          title={voice.name}
          description={voice.description}
          link={`/voiceDetail/${voice.id}`}
        />
      </Carousel.Item>
    );
  });
  const previewList = PageConfig.VOICE_SHOP_TITLES.map((preview, idx) => {
    // 각 프리뷰 리스트 디자인
    return (
      <div className={classes["voice-preview"]} key={idx}>
        <VoicePreview
          title={preview.title}
          subtitle={preview.subtitle}
          voices={stateArr[idx]}
        />
      </div>
    );
  });
  return (
    <>
      <NavigationBar />
      <div style={{ width: "100%", height: "8rem" }} />
      <div className={classes["banner-wrapper"]}>
        <Carousel indicators={false}>{BannerList}</Carousel>
      </div>
      {/* 리스트를 동적으로 가져옴 */}
      {previewList}
      <VoicePreviewAll title="전체 페이지" subtitle="전체 목록" />
    </>
  );
}
