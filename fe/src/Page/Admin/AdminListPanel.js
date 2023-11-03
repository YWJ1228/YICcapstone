import { useEffect, useState } from "react";
import axios from "axios";
import Box from "@mui/material/Box";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemText from "@mui/material/ListItemText";
import { FixedSizeList } from "react-window";

function renderRow(props) {
  const { index, style } = props;

  return (
    <ListItem style={style} key={index} component="div" disablePadding>
      <ListItemButton>
        <ListItemText primary={`Item ${index + 1}`} />
      </ListItemButton>
    </ListItem>
  );
}

export default function AdminListPanel(props) {
  const { value, index } = props;
  const [list, setList] = useState([]);
  useEffect(() => {});
  return (
    <Box sx={{ width: "100%", height: "100%", maxWidth: 240, bgcolor: "background.paper" }}>
      <FixedSizeList height={700} width={240} itemSize={46} itemCount={200} overscanCount={5}>
        {renderRow}
      </FixedSizeList>
    </Box>
  );
}
