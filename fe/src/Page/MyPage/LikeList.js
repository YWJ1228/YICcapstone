import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import classes from './LikeList.module.css';

export default function LikeList(props){
    const likeBooks = (props.bookLike !== undefined && (props.bookLike).map((book, idx)=>{
        return <Col xs = "auto" key = {idx}><a href = {`/bookDetail/${book.ebook.id}`}><img src = {book.ebook.imageUrl} className = {classes['book-image']}/></a></Col>
    }))
    const likeVoices = (props.voiceLike !== undefined && (props.voiceLike).map((voice, idx)=>{
        return <Col xs = "auto" key = {idx}><a href = {`/voiceDetail/${voice.voiceModel.id}`}><img src = {voice.voiceModel.imageUrl} className = {classes['voice-image']}/></a></Col>
    }))
    return(
        <Container>
            <Row className = {classes['sub-title']}>
                E-Book
            </Row>
            <Row>
                {props.bookLike !== undefined ? likeBooks : <div className = {classes['no-item']}>찜한 목록이 없습니다</div>}
            </Row>
            <Row className = {classes['sub-title']}>
                TTS
            </Row>
            <Row>
            {props.voiceLike !== undefined ? likeVoices : <div className = {classes['no-item']}>찜한 목록이 없습니다</div>}
            </Row>
        </Container>
    )
}