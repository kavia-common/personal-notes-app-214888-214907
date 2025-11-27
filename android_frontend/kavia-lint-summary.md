Android Lint Summary (Debug variant)

Generated: Current run via ./gradlew lintDebug
Reports:
- HTML: app/build/reports/lint-results-debug.html
- XML: app/build/reports/lint-results-debug.xml

Overall: 22 warnings, 0 errors

Top issues:
1) Correctness
- FragmentTagUsage (1): Replace <fragment> with FragmentContainerView in res/layout/activity_main.xml.
- GradleDependency (12): Newer versions available for:
  - material 1.12.0 -> 1.13.0
  - appcompat 1.7.0 -> 1.7.1
  - core-ktx 1.13.1 -> 1.17.0
  - recyclerview 1.3.2 -> 1.4.0
  - constraintlayout 2.2.0 -> 2.2.1
  - lifecycle viewmodel/livedata/runtime 2.8.7 -> 2.10.0
  - navigation fragment/ui 2.8.3 -> 2.9.6
  - room runtime/ktx 2.6.1 -> 2.8.4

2) Performance
- Overdraw (4): Root layouts set android:background while theme paints background (activity_main.xml, fragment_note_editor.xml, fragment_notes_list.xml, view_empty_state.xml).
  Suggestion: Remove root backgrounds or use a custom theme with null window background if needed.

- UnusedResources (1): ThemeOverlay.OceanProfessional in res/values/theme_overlays.xml not referenced.
  Suggestion: Remove or reference it.

3) Usability: Icons
- MissingApplicationIcon (1): android:icon missing in AndroidManifest.xml.
  Suggestion: Add a launcher icon (e.g., @mipmap/ic_launcher).

4) Internationalization
- HardcodedText (2): Hardcoded “Title” and “Content preview...” in item_note.xml.
  Suggestion: Move to strings.xml.

- RtlEnabled (1): android:supportsRtl not set in manifest.
  Suggestion: Add android:supportsRtl="true" (or "false") on <application>.

Suggested next steps (not executed here):
- Bump dependency versions after testing compatibility.
- Replace <fragment> with FragmentContainerView in activity_main.xml.
- Clean up root layout backgrounds or theme background.
- Provide app icon and set android:icon.
- Extract hardcoded strings to resources.
- Set android:supportsRtl as appropriate.
- Remove or use ThemeOverlay.OceanProfessional.

End of summary.
