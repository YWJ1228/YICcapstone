import { useState } from "react";
import { PageConfig } from "../../Config/Config";
import { VoiceContext } from "../../Component/Context/AdminContext";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Box from "@mui/material/Box";
import Stack from "@mui/material/Stack";

import classes from "./AdminPage.module.css";
import AdminListPanel from "./AdminListPanel";
import AdminFormPanel from "./AdminFormPanel";

export default function AdminPage() {
  const [value, setValue] = useState(0);
  const [voice, setVoice] = useState(PageConfig.VOICE_PAGE_DEFAULT_STATE);
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  return (
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
      {value === 0 && <AdminListPanel />}
      {value === 1 && (
        <Stack direction="row" spacing={2}>
          <VoiceContext.Provider value={{voice,setVoice}}>
            <AdminListPanel />
            <AdminFormPanel />
          </VoiceContext.Provider>
        </Stack>
      )}
      
      {value === 2 && <AdminListPanel />}
      {value === 3 && <AdminListPanel />}
      {value === 4 && <AdminListPanel />}
    </Box>
  );
}
