import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Stack from "react-bootstrap/Stack";


import Grow from "@mui/material/Grow";

import classes from "./BookPreview.module.css";

export default function BookPreview(props) {
  // 각 상품 카드에 대한 코드
  const imageArr = props.images.map((img, idx) => {
    return (
      <Col key={idx}>
        <Grow in={true} style={{ transformOrigin: "center top" }} {...(props.once ? { timeout: 0 } : { timeout: Math.abs(Math.floor(props.images.length / 2) - idx) * 500 + 1000 })}>
          <div>
            <Row>
              <a href={"/bookDetail/" + img.id}>
                <img src={img.image} className={classes.image} />
              </a>
            </Row>
            <Row className={classes.name}>
              <div>{img.name}</div>
            </Row>
            <Row className={classes.author}>
              <div>{img.author}</div>
            </Row>
            <Row className={classes.price}>
              <div>{img.price}원</div>
            </Row>
          </div>
        </Grow>
      </Col>
    );
  });

  return (
    <Container>
      <Stack className={classes.title}>{props.title}</Stack>
      <Stack direction="horizontal" gap = {3}>{imageArr}</Stack>
    </Container>
  );
}
