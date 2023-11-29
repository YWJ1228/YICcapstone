import { useState, useEffect } from "react";
import { API } from "../../Config/APIConfig";
import axios from "axios";
import { getCookies } from "../Cookies/LoginCookie";

import IconButton from "@mui/material/IconButton";
import ThumbUpAltIcon from '@mui/icons-material/ThumbUpAlt';
import ThumbUpOffAltIcon from '@mui/icons-material/ThumbUpOffAlt';
import classes from "./LikeButton.module.css";

export function LikeButton(props) {
  const [likeState, setLikeState] = useState(false);
  useEffect(() => {
    axios
      .post(
        `${API.LIKE_BUTTON}/${props.id}/preference/verify`,
        {},
        {
          headers: {
            Authorization: `Bearer ${getCookies("accessToken")}`,
          },
        }
      )
      .then((res) => {
        // json 형식에 맞추어서 데이터 넣으면 됨
        setLikeState(res.data);
        console.log(res,1);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);
  function likeClickHandler() {
    setLikeState(likeState * -1 + 1);
    axios
      .post(
        `${API.LIKE_BUTTON}/${props.id}/preference`,
        {},
        {
          headers: {
            Authorization: `Bearer ${getCookies("accessToken")}`,
          },
        }
      )
      .then((res) => {
        // success
       })
      .catch((err) => {
        console.log(err);
      });
  }

  return (
    <IconButton className={classes["like"]} onClick={likeClickHandler}>
      {likeState ? <ThumbUpAltIcon/>: <ThumbUpOffAltIcon/>}
    </IconButton>
  );
}
