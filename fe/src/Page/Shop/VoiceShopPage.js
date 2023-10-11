import {useState, useEffect} from 'react';
import axios from 'axios';

import Carousel from 'react-bootstrap/Carousel';

import BannerCard from "../../Component/Card/BannerCard";

import VoicePreview from '../../Component/Preview/VoicePreview';
import classes from './VoiceShopPage.module.css';

const getBestVoiceListAPI = "http://localhost:8080/voice-model/list?page=0";
const getOnSaleVoiceListAPI = "http://localhost:8080/voice-model/list?page=0";
const getUpdateVoiceListAPI = "http://localhost:8080/voice-model/list?page=0";

export default function () {
    const [bestSellerVoice, setBestSellerVoice] = useState([{
        id : "default",
        image : "default",
        name : "default",
        job : "default",
        description : "default"
    }]);
    const [onSaleVoice, setOnSaleVoice] = useState([{
        id : "default",
        image : "default",
        name : "default",
        job : "default",
        description : "default"
    }]);
    const [updatedVoice, setUpdatedVoice] = useState([{
        id : "default",
        image : "default",
        name : "default",
        job : "default",
        description : "default"
    }]);
    const dummyInfo = [
        { title: "이달의 TTS", subtitle: "이달의 가장 인기있는 목소리를 만나보세요", voices: bestSellerVoice },
        { title: "인기 TTS", subtitle: "인기가 많은 연예인의 목소리로 오디오 북을 들어보세요", voices: onSaleVoice },
        { title: "업데이트 된 TTS", subtitle: "새로 업데이트 된 TTS를 만나보세요", voices: updatedVoice }
    ];
    useEffect(() => {
        axios.all([axios.get(getBestVoiceListAPI), axios.get(getOnSaleVoiceListAPI), axios.get(getUpdateVoiceListAPI)])
            .then(axios.spread((res1, res2,res3) => {
                const resData = (res1.data.content).map((voice) => ({
                    id : voice.id,
                    image : voice.imageUrl,
                    name : voice.celebrityName,
                    job : voice.job,
                    description : voice.comment
                }));
                setBestSellerVoice(resData);
                const resData2 = (res2.data.content).map((voice) => ({
                    id : voice.id,
                    image : voice.imageUrl,
                    name : voice.celebrityName,
                    job : voice.job,
                    description : voice.comment
                }));
                setOnSaleVoice(resData2);
                const resData3 = (res3.data.content).map((voice) => ({
                    id : voice.id,
                    image : voice.imageUrl,
                    name : voice.celebrityName,
                    job : voice.job,
                    description : voice.comment
                }));
                setUpdatedVoice(resData3);
            })).catch((err)=> console.log(err));
    }, []);
    const previewList = dummyInfo.map((preview) => {
        return (
            <div className={classes['voice-preview']} key={preview.title}>
                <VoicePreview
                    title={preview.title}
                    subtitle={preview.subtitle}
                    voices={preview.voices}
                    link={"./"}
                />
            </div>

        );
    });
    return (
        <>
            <div className={classes['banner-wrapper']}>
                <Carousel>
                    <Carousel.Item>
                        <BannerCard imagePath="./logo192.png" title="김채원" description="르세라핌의 김채원의 목소리로 오디오 북을 들어보세요" price="900원" salesDescription="(~9월 24일 까지)" />
                    </Carousel.Item>
                    <Carousel.Item>
                        <BannerCard imagePath="./logo192.png" title="김채원" description="르세라핌의 김채원의 목소리로 오디오 북을 들어보세요" price="900원" salesDescription="(~9월 24일 까지)" />
                    </Carousel.Item>
                </Carousel>
            </div>
            {/* 리스트를 동적으로 가져옴 */}
            {previewList}
        </>
    );
};