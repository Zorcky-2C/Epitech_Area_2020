import {
  Avatar,
  Button,
  Checkbox,
  FormControlLabel,
  Grid,
  Paper,
  TextField,
  Typography,
  Link
} from '@material-ui/core';
import React from 'react';
import { Form, Formik, Field, ErrorMessage } from 'formik';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import * as Yup from 'yup';
import styles from './login.module.css'


const Login = ({ handleChange }) => {
  const avatarStyle = { backgroundColor: '#1bbd7e' }
  const btnstyle = { margin: '8px 0' }
  const initialValues = {
      username: '',
      password: '',
      remember: false
  }

  const validationSchema = Yup.object().shape({
      username: Yup.string().email('Please enter a valid email').required("Required"),
      password: Yup.string().required("Required")
  })

  const onSubmit = (values, props) => {
      setTimeout(() => {
          props.resetForm();
          props.setSubmitting(false);
      }, 2000)
  }
  return (
      <Grid>
          <Paper
              className={styles.paperStyleLogin}>
              <Grid align="center">
                  <Avatar style={avatarStyle}><LockOutlinedIcon /></Avatar>
                  <h2 style={{ marginTop: "0" }}>Sign in</h2>
              </Grid>
              <Formik
                  initialValues={initialValues}
                  onSubmit={onSubmit}
                  validationSchema={validationSchema}
              >
                  {(props) => (
                      <Form>
                          <Field as={TextField} label="Username"
                              name='username'
                              placeholder='Enter username'
                              fullWidth
                              required
                              helperText={<ErrorMessage name="username" />}
                          />
                          <Field as={TextField} label="Password"
                              name='password'
                              placeholder='Enter password'
                              type='password'
                              fullWidth
                              required
                              helperText={<ErrorMessage name="password" />}
                          />
                          <Field as={FormControlLabel}
                              name='remember'
                              control={
                                  <Checkbox
                                      color="primary"
                                  />
                              }
                              label='Remember me'
                          />
                          <Button type='submit'
                              color='primary'
                              variant='contained'
                              disabled={props.isSubmitting}
                              style={btnstyle}
                              fullWidth
                          >
                              {props.isSubmitting ? "Loading" : "Sign in"}
                          </Button>
                      </Form>
                  )}
              </Formik>
              <Typography>
                  <Link href='#' >
                      Forgot password?
                  </Link>
              </Typography>
              <Typography>
                  Do you have an account?
                  <Link hre='#'
                      style={{ cursor: 'pointer' }}
                      onClick={() => handleChange("event", 1)}
                  >
                      <span> </span>Sign Up
                  </Link>
              </Typography>
          </Paper >
      </Grid >
  )
}

export default Login;