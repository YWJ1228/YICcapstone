import { useState, useEffect } from "react";
import NavigationBar from "../../Component/NavigationBar/NavigationBar";
import axios from "axios";

import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Box from "@mui/material/Box";

import { API } from "../../Config/APIConfig";
import classes from "./AdminPage.module.css";
import AdminFormPanel from "./AdminFormPanel";
import { useNavigate } from "react-router-dom";
import { getCookies } from "../../Component/Cookies/LoginCookie";

export default function AdminPage() {
  const [value, setValue] = useState(0);
  const [isAdmin, setIsAdmin] = useState(0);
  const homeNavigate = useNavigate();

  const handleChange = (event, newValue) => {
    setValue(newValue);
    if (newValue === 4) {
      homeNavigate("/");
    }
  };
  // 관리자 정보 확인
  useEffect(() => {
    axios
      .get(`${API.ADMIN_LOAD_FEEDBACK_LIST_SIZE}`, {
        headers: {Authorization : `Bearer ${getCookies('accessToken')}`},
      }).then((res)=>{
        setIsAdmin(1);
      })
      .catch((err) => {
        if (err.response.status === 403) {
          alert("관리자 계정으로 로그인 해주세요");
          homeNavigate("/");
        }
      });
  },[]);
  return (
    <>
      <NavigationBar />
      <div style={{ width: "100%", height: "8rem" }} />
      {isAdmin && (
        <Box className={classes["tab-wrapper"]}>
          <Box sx={{ borderBottom: 1, borderColor: "divider" }} className={classes["nav-wrapper"]}>
            <Tabs value={value} aria-label="basic tabs example" onChange={handleChange}>
              <Tab className={classes["nav-link"]} label="음성모델 관리" />
              <Tab className={classes["nav-link"]} label="Ebook 관리" />
              <Tab className={classes["nav-link"]} label="건의 관리" />
              <Tab
                className={classes["nav-link"]}
                label="종료"
                onClick={() => {
                  homeNavigate("/");
                }}
              />
            </Tabs>
          </Box>
          {value === 0 && <AdminFormPanel type="voice" />}
          {value === 1 && <AdminFormPanel type="ebook" />}
          {value === 2 && <AdminFormPanel type="inquiry" />}
        </Box>
      )}
    </>
  );
}
