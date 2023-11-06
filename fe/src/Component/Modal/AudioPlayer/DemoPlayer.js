import AudioPlayer from "react-h5-audio-player";
import "react-h5-audio-player/lib/styles.css";

import { Modal, Button } from "react-bootstrap";

export default function DemoPlayer(props) {
  const { handlerClose, show } = props.func;
  const voice = props.voiceInfo;

  return (
    <Modal show={show} onHide={handlerClose}>
      <AudioPlayer src="/1.wav" onPlay={(e) => console.log("onPlay")} />
      <Button variant="secondary" onClick={handlerClose}>
        닫기
      </Button>
    </Modal>
  );
}
