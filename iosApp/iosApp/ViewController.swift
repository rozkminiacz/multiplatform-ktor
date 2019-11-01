//
//  ViewController.swift
//  iosApp
//

import UIKit
import common

class ViewController: UIViewController {

    let client = RemoteClient()
    
    fileprivate func loadMessage() {
        client.getMessage{(message) in
            print(message)
            self.label.text = message.message
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
            
        var loop: ((Int) -> Void)!
        loop = { count in
          guard count > 0 else { return }
          self.loadMessage()
          DispatchQueue.main.asyncAfter(deadline: .now() + 10) {
            loop(count - 1)
          }
        }
        loop(50)
        
            
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    @IBOutlet weak var label: UILabel!
}

