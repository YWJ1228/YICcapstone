import { useState,useEffect} from 'react';
import { API } from '../../Config/APIConfig';
import axios from 'axios';
import { getCookies } from '../../Component/Cookies/LoginCookie';
import classes from './HeartButton.module.css';

export function HeartButton(props) {
    const [likeState, setLikeState] = useState(0);
    useEffect(()=>{
        axios.get(
            `${API.LOAD_EBOOK}/${props.id}/preference/verify`,
            {
                headers: {
                    Authorization: `Bearer ${getCookies('accessToken')}`,
                }
            }
        ).then((res)=>{
            // json 형식에 맞추어서 데이터 넣으면 됨
            setLikeState(res.data);
        }).catch((err)=>{console.log(err)})
    },[likeState]);
    // 하트 클릭했을 때 생기는 이벤트
    // 좋아요 추가 및 취소
    // 현재 get 방식으로 구현되어 있음
    function heartClickHandler(){
        setLikeState(likeState * -1 + 1);
        console.log(`${API.LOAD_EBOOK}/${props.id}/preference`);
        axios.get(
            `${API.LOAD_EBOOK}/${props.id}/preference/verify`,
            {
                headers: {
                    Authorization: `Bearer ${getCookies('accessToken')}`,
                }
            }
        )
    }
    
    return (
        <button
            className = {classes['heart-btn']}
            onClick = {heartClickHandler}
        ><img src = {likeState ? "/full_like.png" : "/vacant_like.png"} className={classes.heart}/></button>
    )
}