import { React, useEffect, useState } from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import { makeStyles } from '@material-ui/core/styles';
import axios from 'axios';


const useStyles = makeStyles((theme) => ({
  root: {
    '& .MuiTextField-root': {
      margin: theme.spacing(1),
      width: '1ch',
    },
  },
}));


export function FormDialog() {

  const [open, setOpen] = useState(false);
  const [stock, setStock] = useState('');

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleChange = (event) => {
    setStock({value: event.target.value});
  }


  const updatePost = () => {    
        console.log(this);
        axios.post(process.env.REACT_APP_API_ROOT_URL + '/v1/stocks/' , {
          id:stock.value,
        })
        .then(res => {
          handleClose();
        }).catch(error => {
            console.log('Error Adding Stock' + error);
            handleClose();
          });
  };


  return (
    <div>
      <Button variant="outlined" color="primary" onClick={handleClickOpen}>
      Add Symbol
      </Button>
      <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
        <DialogTitle id="form-dialog-title">Add Symbol</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            required
            margin="normal"
            id="name"
            label="Symbol"
            type="text"
            variant="outlined"
            onChange={handleChange}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={updatePost} color="primary">
            Add
          </Button>
        </DialogActions>
      </Dialog>

    </div>
  );
}
