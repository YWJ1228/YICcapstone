import { useState, useEffect } from "react";
import ListGroup from "react-bootstrap/ListGroup";
import Button from "react-bootstrap/Button";
import classes from "./AdminListPanel.module.css";
export default function AdminListPanel(props) {
  const ItemClickhandler = props.clickHandler;
  const type = props.type;
  const products = props.listOfVoice.map((prd, idx) => {
    return (
      <ListGroup.Item className={classes.list} key = {idx}>
        <Button
          type="submit"
          className={classes.btn}
          onClick={() => {
            ItemClickhandler(prd, type);
          }}
        >
          {type === 'voice' && prd.name}
          {type === 'ebook' && prd.ebookName}
          {type === 'inquiry' && prd.title}
        </Button>
      </ListGroup.Item>
    );
  });
  return (
    <>
      <ListGroup className={classes.scrollbar} >{products}</ListGroup>
    </>
  );
}
