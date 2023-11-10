import { useState } from "react";
import Checkbox from "@mui/material/Checkbox";
import FormGroup from "@mui/material/FormGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import { Button, FormControl, Typography } from "@mui/material";

export default function SearchMenuCard() {
  return (
    <>
      <FormControl onSubmit={() => {
            console.log("1");
          }}>
        <FormGroup
          
        >
          <Typography>상품 카테고리</Typography>
          <hr />
          <FormControlLabel control={<Checkbox defaultChecked />} name="ebook" label="E-Book" />
          <FormControlLabel control={<Checkbox defaultChecked />} name="tts" label="TTS" />
          <br />
          <Typography>검색기준</Typography>
          <hr />
          <FormControlLabel control={<Checkbox defaultChecked />} label="제목" />
          <FormControlLabel control={<Checkbox defaultChecked />} label="저자" />
          <FormControlLabel control={<Checkbox defaultChecked />} label="출판사" />
          <br />

          <Typography>정렬기준</Typography>
          <RadioGroup aria-labelledby="demo-radio-buttons-group-label" defaultValue="female" name="radio-buttons-group">
            <FormControlLabel value="female" control={<Radio />} label="이름순" />
            <FormControlLabel value="male" control={<Radio />} label="업로드순" />
          </RadioGroup>
          
        </FormGroup>
        <Button type="submit">asd</Button>
      </FormControl>
    </>
  );
}
