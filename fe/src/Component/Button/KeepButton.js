import { useState, useEffect } from "react";
import { API } from "../../Config/APIConfig";
import axios from "axios";
import { getCookies } from "../Cookies/LoginCookie";

import classes from "./KeepButton.module.css";

export function KeepButton(props) {
  const [keepState, setKeepState] = useState(false);
  useEffect(() => {
    axios
      .post(
        `${props.type === 'book' ? API.WISH_VERIFY_EBOOK:API.WISH_VERIFY_VOICE}/${props.id}/verify`,
        {},
        {
          headers: {
            Authorization: `Bearer ${getCookies("accessToken")}`,
          },
        }
      )
      .then((res) => {
        // json 형식에 맞추어서 데이터 넣으면 됨
        setKeepState(res.data);
      })
      .catch((err) => {
        // console.log(err);
      });
  }, []);
  function keepClickHandler() {
    
    axios
      .post(
        `${props.type === "book" ? API.WISH_EBOOK : API.WISH_VOICE}${props.id}`,
        {
          
        },
        {
          headers: {
            Authorization: `Bearer ${getCookies("accessToken")}`,
          },
        }
      )
      .then((res) => {
        // success
        setKeepState(keepState * -1 + 1);
       })
      .catch((err) => {
        if(err.response.status === 403){
          alert('로그인이 필요합니다!');
        }
      });
      
  }

  return (
    <button className={classes["keep-btn"]} onClick={keepClickHandler}>
      <img src={keepState ? "/full_like.png" : "/vacant_like.png"} className={classes.keep} />
    </button>
  );
}
