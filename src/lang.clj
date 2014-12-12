(ns lang
  (:require (lang [lexer :as &lexer]
                  [parser :as &parser]
                  [type :as &type]
                  [analyser :as &analyser]
                  [compiler :as &compiler])
            :reload))

(defn write-file [file data]
  (with-open [stream (java.io.BufferedOutputStream. (java.io.FileOutputStream. file))]
    (.write stream data)))

(comment
  (let [source-code (slurp "test2.lang")
        tokens (&lexer/lex source-code)
        ;; _ (prn 'tokens tokens)
        syntax (&parser/parse tokens)
        ;; _ (prn 'syntax syntax)
        class-data (&compiler/compile "test2" syntax)]
    (write-file "test2.class" class-data))

  (->> (slurp "test2.lang")
       &lexer/lex
       &parser/parse
       (&compiler/compile "test2")
       (write-file "test2.class"))

  (->> (slurp "test2.lang")
       &lexer/lex
       &parser/parse
       (&analyser/analyse "test2"))

  (let [source-code (slurp "test2.lang")
        tokens (&lexer/lex source-code)
        ;; _ (prn 'tokens tokens)
        syntax (&parser/parse tokens)
        ;; _ (prn 'syntax syntax)
        ann-syntax (&analyser/analyse "test2" syntax)
        _ (prn 'ann-syntax ann-syntax)
        class-data (&compiler/compile "test2" syntax)]
    (write-file "test2.class" class-data))

  (let [source-code (slurp "test2.lang")
        tokens (&lexer/lex source-code)
        ;; _ (prn 'tokens tokens)
        syntax (&parser/parse tokens)
        ;; _ (prn 'syntax syntax)
        ann-syntax (&analyser/analyse "test2" syntax)
        _ (prn 'ann-syntax ann-syntax)
        class-data (&compiler/compile "test2" ann-syntax)]
    (write-file "test2.class" class-data))

  ;; TODO: Define functions as classes inheriting Function.
  ;; TODO: Add tuples.
  ;; TODO: Add pattern-matching.
  ;; TODO: Do tail-call optimization.
  ;; TODO: Add macros.
  ;; TODO: Add type-level computations.
  ;; TODO: Add interpreter.
  ;; TODO: Add Java-interop.
  ;; TODO: Add signatures & structures OR type-classes.
  ;; TODO: Allow importing Java classes.
  ;; TODO: Allow using other modules.
  ;; TODO: Add thunks.
  ;; TODO: Re-implement compiler in language.
  ;; TODO: 
  ;; TODO: 
  ;; TODO: 
  
  ;; jar cvf test2.jar test2 test2.class
  ;; java -cp "test2.jar" test2
  ;; jar cvf test2.jar test2 test2.class && java -cp "test2.jar" test2
  )
