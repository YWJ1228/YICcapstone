import { useState } from "react";
import NavigationBar from "../../Component/NavigationBar/NavigationBar";
import { Tab, Tabs, Box } from "@mui/material";

import classes from "./ChangeInfoPage.module.css";
import ChangeNickname from "./ChangeInfoPanel/ChangeNickname";
import ChangePassword from "./ChangeInfoPanel/ChangePassword";
import WithdrawalAccount from "./ChangeInfoPanel/WithdrawalAccount";

export default function ChangeInfoPage() {
  const [value, setValue] = useState(0);
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <>
      <NavigationBar />
      <div style={{ width: "100%", height: "6rem" }} />
      <Box className={classes["tab-wrapper"]}>
        <Box sx={{ borderBottom: 1, borderColor: "divider" }} className={classes["nav-wrapper"]}>
          <Tabs value={value} aria-label="basic tabs example" variant="fullWidth"onChange={handleChange}>
            <Tab className={classes["nav-link"]} label="닉네임 변경" />
            <Tab className={classes["nav-link"]} label="비밀번호 변경" />
            <Tab className={classes["nav-link"]} label="회원탈퇴" />
          </Tabs>
        </Box>
          {value === 0 && <ChangeNickname />}
          {value === 1 && <ChangePassword />}
          {value === 2 && <WithdrawalAccount />}
      </Box>
    </>
  );
}
