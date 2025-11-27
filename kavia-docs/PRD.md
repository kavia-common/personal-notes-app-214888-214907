# Personal Notes Android App — Product Requirements Document (PRD)

## Overview
Personal Notes is a lightweight Android application that enables users to capture, view, search, edit, and delete local notes. The app follows a single-activity architecture with fragments for list and editor screens. It uses a monochrome black/white theme for a minimal, distraction-free experience and persists data locally using Room.

## Goals
- Provide a fast, offline-first notes app with zero setup.
- Support core note lifecycle: create, view list, view/edit detail, delete.
- Offer simple search over title and content within the list screen.
- Ensure responsive UI and smooth navigation using ViewBinding and Navigation components.
- Maintain a clean, accessible monochrome design consistent in light and dark modes.

## Personas
- Student/Researcher: Jots down lecture notes, reading summaries, and quick thoughts.
- Busy Professional: Captures action items and meeting notes on-the-go.
- Casual User: Stores quick reminders or ideas without account or sync requirements.

## Assumptions
- Users run Android 11 (API 30) or higher.
- All data is stored locally on device; no cloud sync or account features.
- Notes are simple text entries with a title, content body, and updated timestamp.
- The user interface uses Material components with ViewBinding enabled.
- The theme is monochrome (black/white/gray) with both light and dark mode support.

## In Scope
- Create a note with optional title or content (at least one required).
- View notes list, sorted by last updated time (desc).
- Search notes in the list by title or content.
- Edit an existing note.
- Delete a note with snackbar/undo affordance.
- Local on-device persistence using Room.
- Single-activity with fragments and Navigation.

## Out of Scope
- User accounts, authentication, and multi-device sync.
- Rich text formatting, attachments, or images.
- Sharing/export/import features.
- Reminders/notifications.
- Web or iOS versions.

## User Stories and Acceptance Criteria
### 1) Create Note
- As a user, I can create a new note by tapping the FAB.
- Acceptance:
  - Given I tap the FAB from the list, I navigate to the editor.
  - When I enter a title and/or content and tap Save, the note is persisted and I return to the list.
  - If both title and content are empty, Save is disabled or validation shows an error.

### 2) View Notes List
- As a user, I can see my notes sorted by most recently updated.
- Acceptance:
  - The list shows each note’s title (or “(No title)” if blank) and a preview of content.
  - An empty state is shown when no notes are present.

### 3) Search Notes
- As a user, I can search notes by typing in a search field.
- Acceptance:
  - As I type, the list filters to items whose title or content contains the query (case-insensitive).
  - Clearing the query restores the full list.

### 4) View/Edit Note
- As a user, I can open a note to edit its title or content.
- Acceptance:
  - Selecting a note from the list opens the editor populated with its current data.
  - Tapping Save updates the note and returns me to the list.
  - Updated notes move to the top of the list due to updated timestamp.

### 5) Delete Note
- As a user, I can delete a note from the list.
- Acceptance:
  - Tapping the delete icon removes the note and shows a snackbar with “Undo”.
  - Tapping Undo re-inserts the note with its prior content.

## Non-Functional Requirements
- Performance: List and editor interactions should respond within 100ms on mid-tier devices; navigation transitions under 300ms.
- Reliability: Core functions (create/edit/delete) must persist correctly across app restarts.
- Offline-first: All features work without network connectivity.
- Accessibility: Support TalkBack, adequate color contrast in monochrome theme, touch targets ≥ 48dp, and content descriptions on actionable icons.
- Security/Privacy: Data stored locally in app sandbox; no network transmission. Respect system-level backups based on platform defaults and policies.
- Maintainability: Clear separation of concerns (UI Fragments, ViewModel, Repository, DAO). Use ViewBinding and AndroidX components.
- Compatibility: minSdk 30, targetSdk 34.

## Success Metrics
- Time-to-First-Note: < 30 seconds for a new user to create/save their first note.
- Crash-free sessions: > 99.5% in QA baselines.
- App start to first frame: < 1.5 seconds on mid-tier devices.
- Interaction success: > 95% of test cases pass for CRUD and search.

## Risks & Mitigations
- Risk: Data loss due to accidental deletes.
  - Mitigation: Provide snackbar with Undo on delete.
- Risk: Large notes causing performance issues.
  - Mitigation: Use RecyclerView with DiffUtil and avoid heavy operations on main thread; Room with coroutines for background I/O.
- Risk: Theming inconsistencies across light/dark.
  - Mitigation: Consolidate colors in values/ and values-night/ and test contrast.

## Release Criteria
- Functional:
  - All user stories pass manual QA and automated sanity checks.
  - Editor validation prevents saving fully blank notes.
  - Search correctly filters by title/content.
- Non-Functional:
  - App launches and navigates without crashes on Android 11–14.
  - UI meets basic accessibility checks (content descriptions, contrast).
  - Local data persists across app restarts.
- Documentation:
  - ARCHITECTURE.md completed and up-to-date.
  - In-app strings localized for default locale (English).
- Store Readiness (if applicable):
  - App name, icon, and screenshots prepared (future step; not required for internal release).

## Appendices
- Data model (Room):
  - Note { id: Long (PK, auto), title: String, content: String, updatedAt: Long }
- Monochrome theme:
  - Colors specified in values/ and values-night/; Material3 theme with DayNight inheritance.
