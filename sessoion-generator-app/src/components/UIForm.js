import React from "react";
import { Form, Field } from "react-final-form";
import { TextField, Select } from "final-form-material-ui";
import {
  Paper,
  Grid,
  MenuItem,
  Button,
} from "@material-ui/core";

const axios = require('axios');
const apiCaller = axios.create({
  baseURL: 'http://localhost:8080/',
  timeout: 1000,
  headers: { 'Content-Type': 'application/json' }
});

const onSubmit = async (values) => {
  apiCaller.post('sessiongenerator/v1/generate', JSON.stringify(values))
  .then(function (response) {
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
                      component={Select}
                      label="Athletic level"
                      formControlProps={{ fullWidth: true }}
                    >
                      <MenuItem value="beginner">Beginner</MenuItem>
                    <MenuItem value="intermediate">Intermediate</MenuItem>
                    <MenuItem value="advanced">
                     Advanced
                    </MenuItem>
                    </Field>
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
                      component={Select}
                      label="Sport type"
                      formControlProps={{ fullWidth: true }}
                    >
                        <MenuItem value="swim">Swimming</MenuItem>
                    <MenuItem value="running">Running</MenuItem>
                    </Field>
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
