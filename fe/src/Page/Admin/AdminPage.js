import { useState } from "react";
import { PageConfig } from "../../Config/Config";
import NavigationBar from "../../Component/NavigationBar/NavigationBar";

import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Box from "@mui/material/Box";

import classes from "./AdminPage.module.css";
import AdminFormPanel from "./AdminFormPanel";
import { useNavigate } from "react-router-dom";

export default function AdminPage() {
  const [value, setValue] = useState(0);
  const homeNavigate = useNavigate();

  const handleChange = (event, newValue) => {
    setValue(newValue);
    if (newValue === 4) {
      homeNavigate("/");
    }
  };
  return (
    <>
      <NavigationBar/>
      <div style={{ width: "100%", height: "8rem" }} />
      <Box className={classes["tab-wrapper"]}>
        <Box sx={{ borderBottom: 1, borderColor: "divider" }} className={classes["nav-wrapper"]}>
          <Tabs value={value} aria-label="basic tabs example" onChange={handleChange}>
            <Tab className={classes["nav-link"]} label="오디오북 관리" />
            <Tab className={classes["nav-link"]} label="음성모델 관리" />
            <Tab className={classes["nav-link"]} label="Ebook 관리" />
            <Tab className={classes["nav-link"]} label="회원관리" />
            <Tab className={classes["nav-link"]} label="종료" />
          </Tabs>
        </Box>
        {value === 0 && <div></div>}
        {value === 1 && <AdminFormPanel type="voice" />}
        {value === 2 && <AdminFormPanel type="ebook" />}
        {value === 3 && <AdminFormPanel type="user" />}
      </Box>
    </>
  );
}
