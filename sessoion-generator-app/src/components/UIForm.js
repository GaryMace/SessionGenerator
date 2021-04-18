import React from "react";
import { Form, Field } from "react-final-form";
import { TextField, Checkbox, Radio, Select } from "final-form-material-ui";
import {
  Typography,
  Paper,
  Link,
  Grid,
  Button,
  CssBaseline,
  RadioGroup,
  FormLabel,
  MenuItem,
  FormGroup,
  FormControl,
  FormControlLabel,
} from "@material-ui/core";

const axios = require('axios');
const apiCaller = axios.create({
  baseURL: 'http://localhost:8080/',
  timeout: 1000,
  headers: { 'Content-Type': 'application/json' }
});

const onSubmit = async (values) => {
  console.log(values);
  apiCaller.post('sessiongenerator/v1/generate', JSON.stringify(values))
  .then(function (response) {
    console.log(response);
    return response.json();
  })
  .catch(function (error) {
    console.log(error);
  });
};

const validate = (values) => {
  const errors = {};
  if (!values.athletic_level) {
    errors.athletic_level = "Required";
  }
  if (!values.weekly_session_preference) {
    errors.weekly_session_preference = "Required";
  }
  if (!values.sport_type) {
    errors.sport_type = "Required";
  }
  return errors;
};

export default class UIForm extends React.Component {
  render() {
    return (
      <div style={{ padding: 16, margin: "auto", maxWidth: 600 }}>
        <Form
          onSubmit={onSubmit}
          validate={validate}
          render={({ handleSubmit, reset, submitting, pristine, values }) => (
            <form onSubmit={handleSubmit} noValidate>
              <Paper style={{ padding: 16 }}>
                <Grid container alignItems="flex-start" spacing={2}>
                  <Grid item xs={6}>
                    <Field
                      fullWidth
                      required
                      name="athletic_level"
                      component={TextField}
                      type="enum"
                      label="Athletic level"
                    />
                  </Grid>
                  <Grid item xs={6}>
                    <Field
                      fullWidth
                      required
                      name="weekly_session_preference"
                      component={TextField}
                      type="number"
                      label="Weekly session perference"
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <Field
                      name="sport_type"
                      fullWidth
                      required
                      component={TextField}
                      label="Sport type"
                    />
                  </Grid>
                  <Grid item style={{ marginTop: 16 }}>
                    <Button
                      variant="contained"
                      color="primary"
                      type="submit"
                      disabled={submitting}
                    >
                      Submit
                    </Button>
                  </Grid>
                </Grid>
              </Paper>
              <pre>{JSON.stringify(values, 0, 2)}</pre>
            </form>
          )}
        />
      </div>
    );
  }
}
