import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

import classes from "./PurchaseBook.module.css";

export default function PurchaseBook(props) {
  
  const purchasedBooks = props.books.map((book, idx) => {
    return (
      <Col xs="auto" key={idx}>
        <img src={book.image} className={classes['book-image']} />
      </Col>
    );
  });
  const purchasedVoices = props.voices.map((voice, idx) => {
    return (
      <Col xs="auto" key={idx}>
        <img src={voice.voiceModel.imageUrl} className={classes['voice-image']} />
      </Col>
    );
  });
  return (
    <Container>
      <Row className = {classes['sub-title']}>E-Book</Row>
      <Row>{purchasedBooks}</Row>
      <Row className = {classes['sub-title']}>TTS</Row>
      <Row>{purchasedVoices}</Row>
    </Container>
  );
}
