# Session Generator

*Note: early development*

## ToDo:
- [ ] create naive sessions for a specific profile input
- [ ] Configure the rules for session generation
- [ ] write logic to either email or print the sessions
- [ ] create a UI for the profile input and remove CLI support
- [ ] 

The idea behind this is to eliminate the pain in the ass that is thinking of Swimming and Running sessions for yourself.

Normally these sessions are either hand made by those who know what they are doing or by professional sports scientists. Whilst the sessions that this project generates won't tailor to professional athletes it will issue reasonable training guides that can be loosely followed by sports enthusiast at different sporting abilities.

To Use supply a json file like:
```json
{
  "athletic_level": "beginner", // beginner, intermediate, advanced
  "weekly_session_preference": 2,
  "sport_type": "swim" // swim, run
}
```

the software will generate sessions will the below metadata schema:
```json
{
  "warmup_session_stage_details": {
    ...
  },
  "mainset_session_stage_details": {
    "set_reps": 2,
    "sessions_sets": [
      {
        "set_reps": 4,
        "post_session_rest_seconds": 20,
        "session_set_type": "AEROBIC",
        "set_items": [
          {
            "distance": 100,
            "rest_seconds": 10
          }
        ]
      },
      {
        "set_reps": 2,
        "post_session_rest_seconds": 40,
        "session_set_type": "THRESHOLD",
        "set_items": [
          {
            "distance": 200,
            "rest_seconds": 20
          }
        ]
      }
    ]
  },
  "cooldown_session_stage_details": {
    ...
  }
}
```
The above will translate to a session output of:
```
Warmup:
...
Mainset:
2x (
  4x100m 10sec/rec(aerobic) 
         20sec/rec
  2x200m 20sec/rec(threshold)
         40sec/rec
)
Cooldown:
...
```

`session_set_type` is an important field as it'll indicate to the user the level of effort to apply and also will tell the system what rules apply to each set Item.

For instance, the only `session_set_type` applicable to Cooldown sessions is `RECOVERY`.