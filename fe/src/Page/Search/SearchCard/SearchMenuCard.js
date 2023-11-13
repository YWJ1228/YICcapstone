import { useState } from "react";
import Checkbox from "@mui/material/Checkbox";
import FormGroup from "@mui/material/FormGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import { Button, FormControl, Typography } from "@mui/material";

import classes from './SearchMenuCard.module.css';

export default function SearchMenuCard(props) {
  const setMenuForm = props.setMenu;
  function submitHandler(event){
    event.preventDefault();
    setMenuForm({
      selectedEbook : event.target.ebook.checked,
      selectedTTS : event.target.tts.checked,
      searchStd : event.target.searchStd.value
    })
  }
  return (
    <>
      <form onSubmit = {submitHandler}>
        <Typography>상품 카테고리</Typography>
        <hr />
        <FormControlLabel control={<Checkbox defaultChecked />} name="ebook" label="E-Book" />
        <br/>
        <FormControlLabel control={<Checkbox defaultChecked />} name="tts" label="TTS" />
        <br /><br/>
        <Typography>검색기준</Typography>
        <hr />
        <RadioGroup aria-labelledby="demo-radio-buttons-group-label" defaultValue={'title'} name="searchStd">
          <FormControlLabel value='title' control={<Radio />} label="제목" />
          <FormControlLabel value='author' control={<Radio />} label="저자" />
          <FormControlLabel value='publisher' control={<Radio />} label="출판사" />
        </RadioGroup>
        <br />
        <Button type="submit" className = {classes['search-btn']}>검색</Button>
      </form>
    </>
  );
}
