import { useState, useEffect } from "react";
import ListGroup from "react-bootstrap/ListGroup";
import Button from "react-bootstrap/Button";
import classes from "./AdminListPanel.module.css";
export default function AdminListPanel(props) {
  const ItemClickhandler = props.clickHandler;
  const products = props.listOfVoice.map((prd) => {
    return (
      <ListGroup.Item className={classes.list}>
        <Button
          type="submit"
          className={classes.btn}
          onClick={() => {
            ItemClickhandler(prd);
          }}
        >
          {prd.name}
        </Button>
      </ListGroup.Item>
    );
  });
  return (
    <>
      <ListGroup className={classes.scrollbar}>{products}</ListGroup>
    </>
  );
}
