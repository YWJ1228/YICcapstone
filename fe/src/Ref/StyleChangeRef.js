/**
 *  폼 유효성에 따른 스타일 변경을 위한 Ref 함수
 */

export default function StyleChangeRef(ref, idx, condition) {
    if (condition) {
        ref.current[idx].style.setProperty('border-color', 'green');
    }
    else {
        ref.current[idx].style.setProperty('border-color', 'red');
    }
};