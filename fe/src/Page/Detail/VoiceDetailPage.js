import DetailBanner from "../../Component/ProductDetail/DetailBanner.js.js";
import DetailDescription from "../../Component/ProductDetail/DetailDescription.js";
import DetailReviews from "../../Component/ProductDetail/DetailReviews.js";

import classes from './VoiceDetailPage.module.css';

export default function VoiceDetailPage() {
    const dummyReviews = [
        { id: "usermedfg1", uploadDate: "23.08.10", text: "김채원 TTS 1번 후기입니다." },
        { id: "usnsdfdfgme22", uploadDate: "23.08.12", text: "김채원 TTS 1번 후기입니다.김채원 TTS 1번 후기입니다." },
        { id: "snmasddfge1333", uploadDate: "23.08.13", text: "김채원 TTS 1번 후기입니다.김채원 TTS 1번 후기입니다.김채원 TTS 1번 후기입니다." },
        { id: "urnm4dfg564", uploadDate: "23.08.14", text: "김채원 TTS 1번 후기입니다.김채원 TTS 1번 후기입니다.김채원 TTS 1번 후기입니다.김채원 TTS 1번 후기입니다." },
    ];
    const dummyVoice = {
        name: "김채원",
        job: "가수",
        jobDescription : "르세라핌",
        image: "./logo192.png",
        updatedDate: "23.09.10",
        rating : 3.74,
        price: 900,
        description: " 가수 르세라핌 소속의 김채원 TTS입니다.",
        reviews: dummyReviews
    };

    return (
        <>
            <div className={classes['banner-wrapper']}>
                <DetailBanner book={dummyVoice} type = "voice"/>
            </div>
            <div className={classes.divider}></div>
            <div className={classes['description-wrapper']}>
                <DetailDescription title="TTS 소개" description={dummyVoice.description} />
            </div>
            <div className={classes['reviews-wrapper']}>
                <DetailReviews title="후기" reviews={dummyVoice.reviews} />
            </div>
        </>
    )
}