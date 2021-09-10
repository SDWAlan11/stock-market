import React from "react";
import { makeStyles } from '@material-ui/core/styles';
import TreeItem from '@material-ui/lab/TreeItem';

export function OrderItem({ order }) {
  const { id, sense, quantity, price } = order;

  const useStyles = makeStyles({
    root: {
      height: 240,
      flexGrow: 1,
      maxWidth: 400,
    },
  });

  const label = <span>{sense} {quantity} {price}</span>;

  return (
    <TreeItem nodeId={id} label={label} style={{color: sense === "BUY" ? "green" : "red"}}/>
  );
}
